/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.CrabArmAcquireCommand;
import org.usfirst.frc.team694.robot.commands.CrabArmDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.CrabArmStopCommand;
import org.usfirst.frc.team694.robot.commands.GrabberCloseCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.GrabberToggleCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaAcquireCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDetectFlipCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaFlipDownCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaFlipToggleCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaFlipUpCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaStopCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaTongsLoosenHoldCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaTongsTightenHoldCommand;
import org.usfirst.frc.team694.robot.subsystems.CrabArm;
import org.usfirst.frc.team694.robot.subsystems.Drivetrain;
import org.usfirst.frc.team694.robot.subsystems.Grabber;
import org.usfirst.frc.team694.robot.subsystems.Lift;
import org.usfirst.frc.team694.robot.subsystems.Spatula;

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
   

    private SendableChooser<Command> autonChooser = new SendableChooser<>();
    private Command autonCommand;


    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        spatula = new Spatula();
        crabArm = new CrabArm();
        grabber = new Grabber();
        lift = new Lift();
        oi = new OI();
        SmartDashboard.putData("CrabArms: Acquire", new CrabArmAcquireCommand());
        SmartDashboard.putData("CrabArms: Deacquire", new CrabArmDeacquireCommand());
        SmartDashboard.putData("CrabArms: Stop", new CrabArmStopCommand());
        SmartDashboard.putData("Grabber: Open", new GrabberOpenCommand());
        SmartDashboard.putData("Grabber: Close", new GrabberCloseCommand());
        SmartDashboard.putData("Grabber: Toggle", new GrabberToggleCommand());
        SmartDashboard.putData("Spatula: Acquire", new SpatulaAcquireCommand());
        SmartDashboard.putData("Spatula: Deacquire", new SpatulaDeacquireCommand());
        SmartDashboard.putData("Spatula: Detect Flip", new SpatulaDetectFlipCommand());
        SmartDashboard.putData("Spatula: Flip Up", new SpatulaFlipUpCommand());
        SmartDashboard.putData("Spatula: Toggle Flip", new SpatulaFlipToggleCommand());
        SmartDashboard.putData("Spatula: Flip Down", new SpatulaFlipDownCommand());
        SmartDashboard.putData("Spatula: Stop", new SpatulaStopCommand());
        SmartDashboard.putData("Spatula: Loosen Hold", new SpatulaTongsLoosenHoldCommand());
        SmartDashboard.putData("Spatula: Tighten Hold", new SpatulaTongsTightenHoldCommand());
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
    
    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
