package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RoboTigersSingleSwitchThenOppositeScaleMobilityAutonCommand extends CommandGroup {

    public RoboTigersSingleSwitchThenOppositeScaleMobilityAutonCommand(boolean isRobotRight) {
        addSequential(new DrivetrainMoveInchesEncoderCommand(132-RobotMap.LENGTH_OF_BOT, 1));
        addSequential(new LiftMoveToHeightCommand(30.0));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight ? -90 : 90));
        addSequential(new DrivetrainMoveInchesEncoderCommand(24, 1));
        addSequential(new QuisitorDeacquireCommand(), 0.5);
        addSequential(new DrivetrainMoveInchesEncoderCommand(24, -1));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        addSequential(new DrivetrainMoveInchesEncoderCommand(80, 1));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight ? -90 : 90));
        addSequential(new DrivetrainMoveInchesEncoderCommand(34, 1));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(180));
        addParallel(new QuisitorAcquireCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(30, 1));
        addSequential(new DrivetrainMoveInchesEncoderCommand(30, -1));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight ? -90 : 90));
        addSequential(new DrivetrainMoveInchesEncoderCommand(42, 1));
    }
}
