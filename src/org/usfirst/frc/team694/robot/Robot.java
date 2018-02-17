/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.auton.MobilityAutonUsingEncodersCommand;
import org.usfirst.frc.team694.robot.subsystems.CrabArm;
import org.usfirst.frc.team694.robot.subsystems.Drivetrain;
import org.usfirst.frc.team694.robot.subsystems.Grabber;
import org.usfirst.frc.team694.robot.subsystems.Lift;
import org.usfirst.frc.team694.robot.subsystems.Spatula;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
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
    
    public static boolean isRobotAtRightSideOfDriver;
    private static SendableChooser<Command> autonChooser = new SendableChooser<>();
    private Command autonCommand; // Selected command run during auton

    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        spatula = new Spatula();
        crabArm = new CrabArm();
        grabber = new Grabber();
        lift = new Lift();
        oi = new OI();

        Robot.drivetrain.resetEncoders();

        initSmartDashboard();

<<<<<<< HEAD
=======
        
>>>>>>> b0d5f37d987b2cf75ca54b3a2c525ded786c7e54
        if(isRobotAtRightSideOfDriver == true) {
             
        }
    }

    public enum whereTheBotIsInReferenceToDriver{
        RIGHT_SIDE_OF_DRIVER,
        LEFT_SIDE_OF_DRIVER
    }

    //Bottom means side closer to the scoring table
    public static FieldMapInterface getRobotQuadrant() {
        if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red) {
            if(isRobotAtRightSideOfDriver) {
                return new FieldMapRedFarFromScoringTableQuadrant(); 
            }
            return new FieldMapRedFarFromScoringTableQuadrant();      
        }
        if(isRobotAtRightSideOfDriver) {
            return new FieldMapBlueNearScoringTableQuadrant();
        }
        return new FieldMapBlueNearScoringTableQuadrant();

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
        Robot.drivetrain.resetEncoders(); // TEST
        autonCommand = autonChooser.getSelected();

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
        Robot.drivetrain.resetEncoders(); // TEST
        if (autonCommand != null) {
            autonCommand.cancel();
        }

    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateSmartDashboard();
    }

    private void initSmartDashboard() {

        // AUTON CHOOSER
        autonChooser.addDefault("Do Nothing", new CommandGroup());
        autonChooser.addObject("Mobility", new MobilityAutonUsingEncodersCommand());
        SmartDashboard.putData("Autonomous", autonChooser);

        // Drive Straight rotate PID
        SmartDashboard.putNumber("DriveStraightPID P", 0); 
        SmartDashboard.putNumber("DriveStraightPID I", 0); 
        SmartDashboard.putNumber("DriveStraightPID D", 0);

<<<<<<< HEAD
//        SmartDashboard.putNumber("Lift P", 0);

=======
        SmartDashboard.putBoolean("Is Robot At the Right?", isRobotAtRightSideOfDriver);
        //SmartDashboard.putNumber("Lift P", 0);

        /*SmartDashboard.putNumber("RotateDegreesPID P", 0);
        SmartDashboard.putNumber("RotateDegreesPID I", 0);
        SmartDashboard.putNumber("RotateDegreesPID D", 0);

        SmartDashboard.putNumber("RampSeconds", 2.5);
        */
>>>>>>> b0d5f37d987b2cf75ca54b3a2c525ded786c7e54
        SmartDashboard.putNumber("DriveDistanceEncodersPID P", 0);
        SmartDashboard.putNumber("DriveDistanceEncodersPID I", 0);
        SmartDashboard.putNumber("DriveDistanceEncodersPID D", 0);

<<<<<<< HEAD
        SmartDashboard.putNumber("RampSeconds", 2.5);
        
        SmartDashboard.putBoolean("Is Robot At the Right?", isRobotAtRightSideOfDriver);
        //SmartDashboard.putNumber("Lift P", 0);
=======
>>>>>>> b0d5f37d987b2cf75ca54b3a2c525ded786c7e54
    }
    
    private void updateSmartDashboard() {
        SmartDashboard.putBoolean("Lift: Top Limit Switch", Robot.lift.isAtTop());
        SmartDashboard.putNumber("Lift: Left Encoder Values", Robot.lift.getLeftEncoderDistance());
        SmartDashboard.putNumber("Lift: Right Encoder Values", Robot.lift.getRightEncoderDistance());
        SmartDashboard.putBoolean("Lift: Bottom Limit Switch", Robot.lift.isAtBottom());
        SmartDashboard.putNumber("Lift Speed", Robot.lift.getSpeed());

        SmartDashboard.putBoolean("Drivetrain: Gear Shift", Robot.drivetrain.isGearShift());
        SmartDashboard.putNumber("Drivetrain: Left Encoder Values", Robot.drivetrain.getLeftEncoderDistance());
        SmartDashboard.putNumber("Drivetrain: Right Encoder Values", Robot.drivetrain.getRightEncoderDistance());
        SmartDashboard.putNumber("Drivetrain: Gyro Values", Robot.drivetrain.getGyroAngle());

        //        SmartDashboard.putBoolean("Drivetrain: Left Line Sensor On Line", Robot.drivetrain.leftIsOnLine(0));
        //        SmartDashboard.putBoolean("Drivetrain: Right Line Sensor On Line", Robot.drivetrain.rightIsOnLine(0));
        //        SmartDashboard.putNumber("Drivetrain: Raw Left Line Sensor", Robot.drivetrain.getRawLeftLineSensor());
        //        SmartDashboard.putNumber("Drivetrain: Raw Right Line Sensor", Robot.drivetrain.getRawRightLineSensor());
        SmartDashboard.putBoolean("Spatula: Detect Cube", Robot.spatula.isCubeDetected());
        
    }

    /**
     * S This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

}
