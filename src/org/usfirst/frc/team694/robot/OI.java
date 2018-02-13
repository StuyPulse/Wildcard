/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.CrabArmAcquireCommand;
import org.usfirst.frc.team694.robot.commands.CrabArmDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainHighGearCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainLowGearCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaAcquireCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaFlipToggleCommand;
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

        //operatorGamepad.getLeftButton().whenPressed(new ());
        //        //operatorGamepad.getRightButton().whenPressed(new GrabberToggleCommand());
        //        operatorGamepad.getTopButton().whenPressed(new SpatulaFlipToggleCommand());
        //        operatorGamepad.getBottomButton().whileHeld(new BITCOINManualCommand());
        //         operatorGamepad.getRightTrigger().whileHeld(new BITCOINCheckCommand());
        //        operatorGamepad.getLeftTrigger().whileHeld(new SpatulaDeacquireCommand());
        //        operatorGamepad.getRightTrigger().whileHeld(new SpatulaAcquireCommand());
        //        operatorGamepad.getLeftBumper().whileHeld(new SpatulaDeacquireCommand());
        //        operatorGamepad.getRightBumper().whileHeld(new SpatulaAcquireCommand());
        //
        //        operatorGamepad.getDPadLeft().whileHeld(new CrabArmDeacquireCommand());
        //        operatorGamepad.getDPadRight().whileHeld(new CrabArmAcquireCommand());
        //        operatorGamepad.getDPadUp().whenPressed(new BITCOINAutomationOnCommand());
        //        operatorGamepad.getDPadDown().whenPressed(new BITCOINAutomationOffCommand());

        //Lift command is inside the LiftMoveCommand
    }
}