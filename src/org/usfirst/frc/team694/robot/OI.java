/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.GrabberToggleCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaFlipDownCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaFlipUpCommand;
import org.usfirst.frc.team694.util.Gamepad;
import org.usfirst.frc.team694.util.Gamepad.GamepadSwitchMode;

public class OI {
    public Gamepad driverGamepad;
    public Gamepad operatorGamepad;

    public OI() {
         driverGamepad = new Gamepad(RobotMap.DRIVER_GAMEPAD_PORT, GamepadSwitchMode.PS4);
         operatorGamepad = new Gamepad(RobotMap.OPERATOR_GAMEPAD_PORT, GamepadSwitchMode.SWITCH_X);
         
         operatorGamepad.getRightButton().whenPressed(new GrabberToggleCommand());
         operatorGamepad.getTopButton().whileHeld(new SpatulaFlipUpCommand());
         operatorGamepad.getTopButton().whenReleased(new SpatulaFlipDownCommand());
         operatorGamepad.getLeft
    }
}