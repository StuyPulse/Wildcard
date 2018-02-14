/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.SpatulaAcquireCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;
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

        autonChooser.addDefault("Do Nothing", new CommandGroup());
        autonChooser.addObject("Mobility", new MobilityAutonUsingEncodersCommand());
        SmartDashboard.putData("Autonomous", autonChooser);
        
        SmartDashboard.putBoolean("Is Robot At the Right?", isRobotAtRightSideOfDriver);
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
        autonCommand = autonChooser.getSelected();

        if (autonCommand != null) {
            autonCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        if (autonCommand != null) {
            autonCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    
    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

}
