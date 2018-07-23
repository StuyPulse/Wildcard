/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.DrivetrainHighGearCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainLowGearCommand;
import org.usfirst.frc.team694.robot.commands.LiftDisableOverrideLimitSwitch;
import org.usfirst.frc.team694.robot.commands.LiftDisableRampingCommand;
import org.usfirst.frc.team694.robot.commands.LiftEnableOverrideLimitSwitch;
import org.usfirst.frc.team694.robot.commands.LiftEnableRampingCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.util.Gamepad;
import org.usfirst.frc.team694.util.Gamepad.GamepadSwitchMode;

public class OI {
    public Gamepad driverGamepad;
    public Gamepad operatorGamepad;

    public OI() {
        driverGamepad = new Gamepad(RobotMap.DRIVER_GAMEPAD_PORT, GamepadSwitchMode.PS4);
        //DS4Windows does not work on COMputer
        //driverGamepad = new Gamepad(RobotMap.DRIVER_GAMEPAD_PORT, GamepadSwitchMode.SWITCH_X);
        //Needs to be set to X mode for rumbling to work
        operatorGamepad = new Gamepad(RobotMap.OPERATOR_GAMEPAD_PORT, GamepadSwitchMode.SWITCH_X);

        driverGamepad.getBottomButton().whenPressed(new DrivetrainLowGearCommand());
        driverGamepad.getBottomButton().whenReleased(new DrivetrainHighGearCommand());

        operatorGamepad.getLeftButton().whenPressed(new QuisitorOpenCommand());
        operatorGamepad.getRightButton().whenPressed(new QuisitorCloseCommand());
        //        operatorGamepad.getTopButton().whenPressed(new QuisitorOpenCommand());
        //        operatorGamepad.getBottomButton().whenPressed(new QuisitorCloseCommand());

        //operatorGamepad.getRightBumper().whileHeld(new QuisitorAcquireCommand());
        operatorGamepad.getLeftBumper().whileHeld(new QuisitorMoveSpeedCommand(-0.4));
        operatorGamepad.getRightTrigger().whileHeld(new QuisitorMoveSpeedCommand(1.0));
        operatorGamepad.getLeftTrigger().whileHeld(new QuisitorMoveSpeedCommand(-1.0));

        operatorGamepad.getDPadRight().whenPressed(new LiftEnableRampingCommand());
        operatorGamepad.getSelectButton().whenPressed(new LiftDisableRampingCommand()); //back button
        operatorGamepad.getDPadRight().whenPressed(new LiftDisableRampingCommand());
        operatorGamepad.getDPadLeft().whenPressed(new LiftEnableRampingCommand());
        operatorGamepad.getDPadDown().whenPressed(new LiftEnableOverrideLimitSwitch());
        operatorGamepad.getDPadUp().whenPressed(new LiftDisableOverrideLimitSwitch());

        operatorGamepad.getStartButton().whileHeld(new LiftMoveSpeedCommand(-1 * RobotMap.LIFT_BACKDRIVE_SPEED));
        //        operatorGamepad.getRightButton().whenPressed( new PrepareForClimbCommand());

        /// TESTING
        //        driverGamepad.getDPadUp().whenPressed(new LiftMoveToHeightCommand(86.0));
        //        driverGamepad.getDPadUp().whenPressed(new TestCommand());
        //        driverGamepad.getDPadUp().whenPressed(new DriveStraightRampDownOnlyCommand(250));
        //        driverGamepad.getDPadDown().whenPressed(new DriveStraightRampDownOnlyCommand(-250));
//                driverGamepad.getDPadUp().whenPressed(new DrivetrainRotateAbsoluteDegreesPIDCommand(45));
//               driverGamepad.getDPadRight().whenPressed(new DrivetrainRotateRelativeDegreesPIDCommand(90));
//               driverGamepad.getDPadDown().whenPressed(new DrivetrainRotateRelativeDegreesPIDCommand(-90));
//               

    }
}
