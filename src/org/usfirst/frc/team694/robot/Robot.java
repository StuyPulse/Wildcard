/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.*;
import org.usfirst.frc.team694.robot.subsystems.Acquirer;
import org.usfirst.frc.team694.robot.subsystems.CrabArm;
import org.usfirst.frc.team694.robot.subsystems.Drivetrain;
import org.usfirst.frc.team694.robot.subsystems.Grabber;
import org.usfirst.frc.team694.robot.subsystems.Lift;
import org.usfirst.frc.team694.util.IRSensor;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

    public static Drivetrain drivetrain;
    public static Acquirer acquirer;
    public static CrabArm crabArm;
    public static Grabber grabber; 
    public static IRSensor irsensor;  
    public static Lift lift;
 
    public static OI oi;
   

    private SendableChooser<Command> autonChooser = new SendableChooser<>();
    private Command autonCommand; // Selected command run during auton

    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        acquirer = new Acquirer();
        crabArm = new CrabArm();
        grabber = new Grabber();
        lift = new Lift();
        oi = new OI();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        autonCommand = autonChooser.getSelected();

        if (autonCommand != null) {
            autonCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.getNumber("IR Sensor Voltage", IRSensor.getSensorVoltage());
    }

    @Override
    public void teleopInit() {
        if (autonCommand != null) {
            autonCommand.cancel();
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        liftRun();
        acquirerStatus();
        dequirerRun();
    }

    private void liftRun() {
        if(oi.operatorGamepad.getLeftY() > 0.9) {
            Scheduler.getInstance().add(new LiftMoveCommand(1));     
        }
        else if (oi.operatorGamepad.getLeftY() > 0.4) {
            Scheduler.getInstance().add(new LiftMoveCommand(0.5));  
        }
        else if (oi.operatorGamepad.getLeftY() > -0.4) {
            Scheduler.getInstance().add(new LiftStopCommand());
        }
        else if (oi.operatorGamepad.getLeftY() > -0.9) {
            Scheduler.getInstance().add(new LiftMoveCommand(-0.5));
        }
        else {
            Scheduler.getInstance().add(new LiftMoveCommand(-1));
        }
    }
    private void acquirerStatus() {
        // If statement checks to make sure that the Right Trigger is the only trigger pressed to prevent both triggers from being pressed at the same time
        if(oi.operatorGamepad.getRawRightTriggerAxis() > 0.5 && oi.operatorGamepad.getRawLeftTriggerAxis() < 0.5) {
            Robot.acquirer.setAcquirerRunning(true);
            Scheduler.getInstance().add(new AcquirerAcquireCommand() );    
        }
        else if (oi.operatorGamepad.getRawLeftTriggerAxis() > 0.5 && oi.operatorGamepad.getRawRightTriggerAxis() < 0.5) {
            Robot.acquirer.setAcquirerRunning(true);
            Scheduler.getInstance().add(new AcquirerDeacquireCommand());
        }
        else {
            Robot.acquirer.setAcquirerRunning(false);
        }
    }
    
    private void dequirerRun() {
     // If statement checks to make sure that the Left Trigger is the only trigger pressed to prevent both triggers from being pressed at the same time
        if (oi.operatorGamepad.getRawLeftTriggerAxis() > 0.5 && oi.operatorGamepad.getRawRightTriggerAxis() < 0.5) {
            Robot.acquirer.setAcquirerRunning(true);
            Scheduler.getInstance().add(new AcquirerDeacquireCommand());
        }
    }
    
    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
