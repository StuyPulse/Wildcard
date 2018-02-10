/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.*;
import org.usfirst.frc.team694.robot.subsystems.Spatula;
import org.usfirst.frc.team694.robot.subsystems.CrabArm;
import org.usfirst.frc.team694.robot.subsystems.Drivetrain;
import org.usfirst.frc.team694.robot.subsystems.Grabber;
import org.usfirst.frc.team694.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
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
   
    public static FieldMapTopLeftQuadrant TopLeftQuad;
    public static FieldMapTopRightQuadrant TopRightQuad;
    public static FieldMapBottomLeftQuadrant BottomLeftQuad;
    public static FieldMapBottomRightQuadrant BottomRightQuad;
    
    static boolean isRobotAtBottom;
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
        SmartDashboard.getNumber("IR Sensor Voltage", IRSensor.getSensorVoltage());
        isRobotAtBottom = SmartDashboard.putBoolean("Donde es nuestro robot? Wo men de ji chi ren zai nar?", false);
        //SmartDashboard.getNumber("IR Sensor Voltage", IRSensor.getSensorVoltage());
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
        acquirerStatus();
        dequirerRun();
    }

    
    private void acquirerStatus() {
        // If statement checks to make sure that the Right Trigger is the only trigger pressed to prevent both triggers from being pressed at the same time
        if(oi.operatorGamepad.getRawRightTriggerAxis() > 0.5 && oi.operatorGamepad.getRawLeftTriggerAxis() < 0.5) {
            Robot.spatula.setSpatulaRunning(true);
            Scheduler.getInstance().add(new SpatulaAcquireCommand() );    
        }
        else if (oi.operatorGamepad.getRawLeftTriggerAxis() > 0.5 && oi.operatorGamepad.getRawRightTriggerAxis() < 0.5) {
            Robot.spatula.setSpatulaRunning(true);
            Scheduler.getInstance().add(new SpatulaDeacquireCommand());
        }
        else {
            Robot.spatula.setSpatulaRunning(false);
        }
    }
    
    private void dequirerRun() {
     // If statement checks to make sure that the Left Trigger is the only trigger pressed to prevent both triggers from being pressed at the same time
        if (oi.operatorGamepad.getRawLeftTriggerAxis() > 0.5 && oi.operatorGamepad.getRawRightTriggerAxis() < 0.5) {
            Robot.spatula.setSpatulaRunning(true);
            Scheduler.getInstance().add(new SpatulaDeacquireCommand());
        }
    }
    
    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
    //Bottom means side closer to the scoring table
    public static FieldMapInterface getRobotQuadrant() {
        if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red) {
            if(isRobotAtBottom) {
                return BottomLeftQuad;
            }
            return TopLeftQuad;       
        }
        if(isRobotAtBottom) {
            return BottomRightQuad;
        }
        return TopRightQuad;
    }
}
