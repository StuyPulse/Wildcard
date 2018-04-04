/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

import org.usfirst.frc.team694.robot.commands.DrivetrainHighGearCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainLowGearCommand;
import org.usfirst.frc.team694.robot.commands.FlashPurple;
import org.usfirst.frc.team694.robot.commands.LiftMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorToggleCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
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

        operatorGamepad.getLeftButton().whenPressed( new QuisitorToggleCommand());
        operatorGamepad.getRightButton().whenPressed(new QuisitorToggleCommand());
        operatorGamepad.getTopButton().whenPressed(new QuisitorOpenCommand());
        operatorGamepad.getBottomButton().whenPressed(new QuisitorCloseCommand());
        
        //operatorGamepad.getRightBumper().whileHeld(new QuisitorAcquireCommand());
        operatorGamepad.getLeftBumper().whileHeld(new QuisitorMoveSpeedCommand(- 0.4));
        operatorGamepad.getRightTrigger().whileHeld(new QuisitorMoveSpeedCommand(1.0));
        operatorGamepad.getLeftTrigger().whileHeld(new QuisitorMoveSpeedCommand(-1.0));
        
        operatorGamepad.getStartButton().whileHeld(new LiftMoveSpeedCommand(-1 * RobotMap.LIFT_BACKDRIVE_SPEED));
//        operatorGamepad.getRightButton().whenPressed( new PrepareForClimbCommand());
        operatorGamepad.getDPadUp().whenPressed(new FlashPurple());

        /// TESTING
//        driverGamepad.getDPadUp().whenPressed(new LiftMoveToHeightCommand(86.0));
//        driverGamepad.getDPadUp().whenPressed(new TestCurveCommand());
//        driverGamepad.getDPadUp().whenPressed(new DriveStraightRampDownOnlyCommand(250));
//        driverGamepad.getDPadDown().whenPressed(new DriveStraightRampDownOnlyCommand(-250));
        driverGamepad.getDPadUp().whenPressed(new DrivetrainRotateAbsoluteDegreesPIDCommand(45));
        driverGamepad.getDPadRight().whenPressed(new DrivetrainRotateAbsoluteDegreesPIDCommand(90));

    }
}
