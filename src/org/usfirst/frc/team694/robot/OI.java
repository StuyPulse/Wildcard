/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.BITCOINAutomationOffCommand;
import org.usfirst.frc.team694.robot.commands.BITCOINAutomationOnCommand;
import org.usfirst.frc.team694.robot.commands.BITCOINCheckCommand;
import org.usfirst.frc.team694.robot.commands.BITCOINCommand;
import org.usfirst.frc.team694.robot.commands.CrabArmFlapOutCommand;
import org.usfirst.frc.team694.robot.commands.CrabArrowAcquireCommand;
import org.usfirst.frc.team694.robot.commands.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainHighGearCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainLowGearCommand;
import org.usfirst.frc.team694.robot.commands.GrabberToggleCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaFlipToggleCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaLeftDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaRightDeacquireCommand;
import org.usfirst.frc.team694.util.Gamepad;
import org.usfirst.frc.team694.util.Gamepad.GamepadSwitchMode;

public class OI {
    public Gamepad driverGamepad;
    public Gamepad operatorGamepad;

    public OI() {
        driverGamepad = new Gamepad(RobotMap.DRIVER_GAMEPAD_PORT, GamepadSwitchMode.PS4);
        operatorGamepad = new Gamepad(RobotMap.OPERATOR_GAMEPAD_PORT, GamepadSwitchMode.SWITCH_D);

        driverGamepad.getBottomButton().whenPressed(new DrivetrainLowGearCommand());
        driverGamepad.getBottomButton().whenReleased(new DrivetrainHighGearCommand());

        // TESTING ONLY
        //driverGamepad.getDPadUp().whenPressed(new DriveStraightPIDCommand(100, 0.75));
        //driverGamepad.getDPadUp().whenPressed(new );
        driverGamepad.getDPadUp().whenPressed(new DriveStraightWithRampingCommand(100));

        //        operatorGamepad.getBottomButton().whileHeld(new BITCOINManualCommand());
        operatorGamepad.getRightTrigger().whileHeld(new BITCOINCheckCommand());
        
        operatorGamepad.getLeftButton().whileHeld(new CrabArmFlapOutCommand());
        operatorGamepad.getRightButton().whenPressed(new GrabberToggleCommand());
        operatorGamepad.getTopButton().whenPressed(new SpatulaFlipToggleCommand());
        operatorGamepad.getBottomButton().whileHeld(new BITCOINCommand());
        operatorGamepad.getLeftTrigger().whileHeld(new SpatulaDeacquireCommand());
        //operatorGamepad.getRightTrigger().whileHeld(new CrabArrowAcquireCommand());
        
        operatorGamepad.getLeftBumper().whileHeld(new SpatulaRightDeacquireCommand());
        operatorGamepad.getRightBumper().whileHeld(new SpatulaLeftDeacquireCommand());
        
        //this speed lets the robot "brake" when it's at its desired height
//        operatorGamepad.getDPadUp().whileHeld(new LiftMoveSpeedCommand(0.16667));
//        operatorGamepad.getDPadDown().whileHeld(new LiftMoveSpeedCommand(-0.16667));

        operatorGamepad.getDPadUp().whenPressed(new BITCOINAutomationOnCommand());
        operatorGamepad.getDPadDown().whenPressed(new BITCOINAutomationOffCommand());

        // TODO: This should probably be a separate command
        operatorGamepad.getStartButton().whileHeld(new LiftMoveSpeedCommand(RobotMap.LIFT_BRAKE_SPEED));
        
    }
}