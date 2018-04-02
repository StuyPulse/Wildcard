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
import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.PrepareForClimbCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorToggleCommand;
import org.usfirst.frc.team694.robot.commands.TestCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.util.Gamepad;
import org.usfirst.frc.team694.util.Gamepad.GamepadSwitchMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        operatorGamepad.getRightTrigger().whileHeld(new QuisitorAcquireCommand());
        operatorGamepad.getLeftTrigger().whileHeld(new QuisitorDeacquireCommand());

        operatorGamepad.getStartButton().whileHeld(new LiftMoveSpeedCommand(-1 * RobotMap.LIFT_BACKDRIVE_SPEED));
//        operatorGamepad.getRightButton().whenPressed( new PrepareForClimbCommand());

        /// TESTING
        driverGamepad.getDPadUp().whenPressed(new LiftMoveToHeightCommand(86.0));
//        driverGamepad.getDPadUp().whenPressed(new TestCurveCommand());
//        driverGamepad.getDPadUp().whenPressed(new DriveStraightWithRampingCommand(100));
//        driverGamepad.getDPadRight().whenPressed(new DrivetrainRotateAbsoluteDegreesPIDCommand(90));

    }
}
