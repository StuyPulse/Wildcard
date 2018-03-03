/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.auton.LeftSideSwitchAutonCommand;
import org.usfirst.frc.team694.robot.commands.auton.MobilityAutonCommand;
import org.usfirst.frc.team694.robot.commands.auton.RightSideSwitchAutonCommand;
import org.usfirst.frc.team694.robot.commands.auton.SameSideScaleAutonCommand;
import org.usfirst.frc.team694.robot.commands.auton.SideScaleAutonChooserCommand;
import org.usfirst.frc.team694.robot.commands.auton.SideSwitchAutonChooserCommand;
import org.usfirst.frc.team694.robot.commands.auton.SimpleDifferentSideScaleAutonCommand;
import org.usfirst.frc.team694.robot.subsystems.CrabArm;
import org.usfirst.frc.team694.robot.subsystems.Drivetrain;
import org.usfirst.frc.team694.robot.subsystems.Grabber;
import org.usfirst.frc.team694.robot.subsystems.Lift;
import org.usfirst.frc.team694.robot.subsystems.Spatula;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

    public static Drivetrain drivetrain;
    public static Spatula spatula;
    public static CrabArm crabArm;
    public static Grabber grabber;
    public static Lift lift;

    public static OI oi;

    static boolean isRobotAtBottom;

    private String gameData;
    public static boolean isRobotOnRight;

    private static boolean isAllianceSwitchRight;
    private static boolean isScaleRight;

    private static SendableChooser<Command> autonChooser = new SendableChooser<>();
    private Command autonCommand; // Selected command run during auton
    private static SendableChooser<WhereTheBotIsInReferenceToDriver> sideChooser = new SendableChooser<>();

//    private PowerDistributionPanel pdppanel;

    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        spatula = new Spatula();
        crabArm = new CrabArm();
        grabber = new Grabber();
        lift = new Lift();
        oi = new OI();

        //        pdppanel = new PowerDistributionPanel();

        //        autonChooser.addDefault("Do Nothing", new CommandGroup());
        //        autonChooser.addObject("Mobility", new MobilityAutonUsingEncodersCommand());
        //        SmartDashboard.putData("Autonomous", autonChooser);
        //        
        //        sideChooser.addObject("Right of Driver", WhereTheBotIsInReferenceToDriver.RIGHT_SIDE_OF_DRIVER);
        //        sideChooser.addObject("Left Side of Driver", WhereTheBotIsInReferenceToDriver.LEFT_SIDE_OF_DRIVER);
        //        SmartDashboard.putData("Where is the robot starting?", sideChooser);
        //        
        //        SmartDashboard.putNumber("Lift P", 0);
        //        
        //        SmartDashboard.putNumber("RotateDegreesPID P", 0);
        //        SmartDashboard.putNumber("RotateDegreesPID I", 0);
        //        SmartDashboard.putNumber("RotateDegreesPID D", 0);

        initSmartDashboard();
    }

    public enum WhereTheBotIsInReferenceToDriver {
        RIGHT_SIDE_OF_DRIVER, LEFT_SIDE_OF_DRIVER
    }

    //Bottom means side closer to the scoring table
    public static FieldMapInterface getRobotQuadrant() {
        // TESTING ONLY
        return new FieldMapRedFarFromScoringTableQuadrant();

        /*if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red) {
            if(isRobotOnRight) {
                return new FieldMapRedFarFromScoringTableQuadrant(); 
            }
            return new FieldMapRedNearScoringTableQuadrant();      
        }
        if(isRobotOnRight) {
            return new FieldMapBlueNearScoringTableQuadrant();
        }
        return new FieldMapBlueFarFromScoringTableQuadrant();
        */
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        Scheduler.getInstance().removeAll();

        drivetrain.resetEncoders();
        drivetrain.resetGyro();
        drivetrain.resetGyroError();

        gameData = null;

        double timestamp = Timer.getFPGATimestamp();
        while ((Timer.getFPGATimestamp() - timestamp) < 5 && (gameData == null || gameData.isEmpty())) {
            gameData = DriverStation.getInstance().getGameSpecificMessage();
        }
        if (gameData == null || gameData.isEmpty()) {//If there is no field data run mobility
            autonCommand = new MobilityAutonCommand();
            System.err.print("******* Field Data Problem!!!");
            System.err.println("Please yell at the field management crew to fix this");
        } else {
            isRobotOnRight = sideChooser.getSelected() == WhereTheBotIsInReferenceToDriver.RIGHT_SIDE_OF_DRIVER;
            isAllianceSwitchRight = gameData.charAt(0) == 'R';
            isScaleRight = gameData.charAt(1) == 'R';
            autonCommand = autonChooser.getSelected();
        }

        if (autonCommand != null) {
            autonCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateSmartDashboard();
    }

    @Override
    public void teleopInit() {
        drivetrain.resetEncoders();
        drivetrain.resetGyro();
        drivetrain.resetGyroError();

        Robot.drivetrain.setRamp(0.0);
        if (autonCommand != null) {
            autonCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateSmartDashboard();
    }

    @Override
    public void robotPeriodic() {
        /// Current Limiting
    }

    private void initSmartDashboard() {
        // AUTON CHOOSER
        autonChooser.addDefault("Do Nothing", new CommandGroup());
        autonChooser.addObject("Mobility", new MobilityAutonCommand());
        /* Testing autons:
        autonChooser.addObject("Same Side Scale Auton", new SameSideScaleAutonCommand());
        autonChooser.addObject("Different Side Scale Auton", new DifferentSideScaleAutonCommand());
        autonChooser.addObject("Right Side Switch Auton", new RightSideSwitchAutonCommand());
        autonChooser.addObject("Left Side Switch Auton", new LeftSideSwitchAutonCommand());
        */

        autonChooser.addDefault("SWITCH ALWAYS Auton", new SideSwitchAutonChooserCommand());
        autonChooser.addObject("SCALE ALWAYS Auton", new SideScaleAutonChooserCommand());
        autonChooser.addObject("FORCE LEFT SWITCH Auton", new LeftSideSwitchAutonCommand());
        autonChooser.addObject("FORCE RIGHT SWITCH Auton", new RightSideSwitchAutonCommand());
        autonChooser.addObject("FORCE SAME SIDE SCALE Auton", new SameSideScaleAutonCommand());
        autonChooser.addObject("FORCE DIFFERENT SIDE SCALE Auton", new SimpleDifferentSideScaleAutonCommand());
        SmartDashboard.putData("Autonomous", autonChooser);

        // SIDE CHOOSER
        sideChooser.addDefault("Right", WhereTheBotIsInReferenceToDriver.RIGHT_SIDE_OF_DRIVER);
        sideChooser.addObject("Left", WhereTheBotIsInReferenceToDriver.LEFT_SIDE_OF_DRIVER);
        SmartDashboard.putData("Where is the bot starting?", sideChooser);

        // PDP Panel
        //        SmartDashboard.putData("PDP", pdppanel);

        SmartDashboard.putNumber("Lift P", 0.3);

        SmartDashboard.putNumber("DriveStraight RampSeconds", 0.0);

        // Drive Straight Distance PID
        SmartDashboard.putNumber("DriveDistanceEncodersPID P", 0.01);
        SmartDashboard.putNumber("DriveDistanceEncodersPID I", 0);
        SmartDashboard.putNumber("DriveDistanceEncodersPID D", 0.08);

        // Drive Straight Rotation PID
        SmartDashboard.putNumber("DriveStraightGyroPID P", 0.012);
        SmartDashboard.putNumber("DriveStraightGyroPID I", 0);
        SmartDashboard.putNumber("DriveStraightGyroPID D", 0.2);

        SmartDashboard.putNumber("RotateDegreesPID P", 0.037);
        SmartDashboard.putNumber("RotateDegreesPID I", 0.0);
        SmartDashboard.putNumber("RotateDegreesPID D", 0.1);

        SmartDashboard.putNumber("RotateDegreesPID RampSeconds", 0.0);

        SmartDashboard.putNumber("DriveStraight Encoder Vel", 0);
    }

    private void updateSmartDashboard() {

        //        SmartDashboard.putData(pdppanel);

        SmartDashboard.putBoolean("Lift: Top Limit Switch", Robot.lift.isAtTop());
        SmartDashboard.putNumber("Lift: Left Encoder Values", Robot.lift.getLeftEncoderDistance());
        SmartDashboard.putNumber("Lift: Right Encoder Values", Robot.lift.getRightEncoderDistance());
        SmartDashboard.putBoolean("Lift: Bottom Limit Switch", Robot.lift.isAtBottom());
        SmartDashboard.putNumber("Lift Speed", Robot.lift.getSpeed());

        SmartDashboard.putBoolean("Drivetrain: Gear Shift", Robot.drivetrain.isGearShift());
        SmartDashboard.putNumber("Drivetrain: Left Encoder Values", Robot.drivetrain.getLeftEncoderDistance());
        SmartDashboard.putNumber("Drivetrain: Right Encoder Values", Robot.drivetrain.getRightEncoderDistance());
        SmartDashboard.putNumber("Drivetrain: RAW Left Encoder Values", Robot.drivetrain.getLeftRawEncoderDistance());
        SmartDashboard.putNumber("Drivetrain: RAW Right Encoder Values", Robot.drivetrain.getRightRawEncoderDistance());
        SmartDashboard.putNumber("Drivetrain: Gyro Value", Robot.drivetrain.getGyroAngle());
        SmartDashboard.putNumber("Drivetrain: Gyro ABSOLUTE", Robot.drivetrain.getAbsoluteGyroAngle());

        SmartDashboard.putBoolean("Drivetrain: Left Line Sensor On Line", Robot.drivetrain.leftIsOnLine());
        SmartDashboard.putBoolean("Drivetrain: Right Line Sensor On Line", Robot.drivetrain.rightIsOnLine());
        SmartDashboard.putNumber("Drivetrain: Raw Left Line Sensor", Robot.drivetrain.getRawLeftLineSensor());
        SmartDashboard.putNumber("Drivetrain: Raw Right Line Sensor", Robot.drivetrain.getRawRightLineSensor());

        SmartDashboard.putBoolean("Spatula: Detect Cube", Robot.spatula.isCubeDetected());
        SmartDashboard.putBoolean("Spatula: Is up?", Robot.spatula.isSpatulaUp());

        SmartDashboard.putNumber("Lift Current", lift.getCurrent());
        SmartDashboard.putNumber("Drivetrain Current", drivetrain.getCurrent());
        SmartDashboard.putNumber("Lift + Drivetrain Current", lift.getCurrent() + drivetrain.getCurrent());

    }

    /**
     * S This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

    public static boolean isRobotAndSwitchOnSameSide() {
        return (isAllianceSwitchRight && isRobotOnRight) || (!isAllianceSwitchRight && !isRobotOnRight);
        //true is switch is close to robot
        //false is switch is far away robot
    }

    public static boolean isRobotAndScaleOnSameSide() {
        return (isScaleRight && isRobotOnRight) || (!isScaleRight && !isRobotOnRight);
        //true is scale is close to robot 
        //false is scale is far away from robot 
    }

}
