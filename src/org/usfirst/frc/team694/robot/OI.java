/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.DrivetrainHighGearCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainLowGearCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorToggleCommand;
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
        //So half of OI is now commented out! If we want there to be an actual, um, OI, we need to fix this up.
        //We should probably talk about this stuff. :/
        // TESTING ONLY
//        driverGamepad.getDPadUp().whenPressed(new DriveStraightWithRampingCommand(200));
//          driverGamepad.getDPadUp().whenPressed(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
//          driverGamepad.getDPadRight().whenPressed(new DrivetrainRotateRelativeDegreesPIDCommand(90));
//        driverGamepad.getDPadUp().whenPressed(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT));
        //        operatorGamepad.getBottomButton().whileHeld(new BITCOINManualCommand());
        //operatorGamepad.getRightTrigger().whileHeld(new BITCOINCheckCommand());

   //   operatorGamepad.getLeftButton().whileHeld(new CrabArmFlapOutCommand()); //TODO: What should this do?
        operatorGamepad.getRightButton().whenPressed(new QuisitorToggleCommand());
//        operatorGamepad.getTopButton().whenPressed(new SpatulaFlipToggleCommand());
        // Prev line replaced with:
        operatorGamepad.getRightTrigger().whileHeld(new QuisitorAcquireCommand());
   //   operatorGamepad.getBottomButton().whenPressed(new BITCOINCommand());
        operatorGamepad.getLeftTrigger().whileHeld(new QuisitorDeacquireCommand());
   //   operatorGamepad.getRightTrigger().whileHeld(new BITCOINCheckCommand());

    //  operatorGamepad.getDPadUp().whenPressed(new BITCOINAutomationOnCommand());
   //   operatorGamepad.getDPadDown().whenPressed(new BITCOINAutomationOffCommand());

        // TODO: This should probably be a separate command
        operatorGamepad.getStartButton().whileHeld(new LiftMoveSpeedCommand(-1 * RobotMap.LIFT_BACKDRIVE_SPEED));
        
    }
}
