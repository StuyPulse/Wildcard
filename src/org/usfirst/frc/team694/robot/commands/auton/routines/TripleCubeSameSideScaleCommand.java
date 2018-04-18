package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * WITH CURVES
 */
public class TripleCubeSameSideScaleCommand extends CommandGroup {
    private static final double DISTANCE_TOTAL = 296 - 4;
    public TripleCubeSameSideScaleCommand(boolean isRight) {
        
        
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL);
        driveCommand.addSpeedChange(0, 0.75);
        driveCommand.addTurn(130, isRight ? -45 : 45);
        driveCommand.addTurn(130 + 116, isRight ? 5 : -5);
        
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT - 12), 15));
        addSequential(driveCommand, 3.3);
        addSequential(new QuisitorMoveSpeedCommand(-0.7), 0.5);
        addParallel(new LiftMoveToBottomCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(-15, 0.4));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -160 : 160));
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand(), 2);
        addSequential(new DriveStraightRampDownOnlyCommand(60));
        addSequential(new QuisitorCloseCommand());
        addParallel(new DrivetrainMoveInchesEncoderCommand(15, -0.4));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        addParallel(new LiftMoveToHeightCommand(68));
        addSequential(new DrivetrainMoveInchesEncoderCommand(62, 0.5));
        addSequential(new QuisitorMoveSpeedCommand(-0.5), 1);
        addParallel(new LiftMoveToBottomCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(32, -0.4));
        addParallel(new QuisitorOpenCommand());
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(135));
        addParallel(new QuisitorAcquireCommand(), 2);
        addSequential(new DriveStraightRampDownOnlyCommand(40));
        addParallel(new LiftMoveToHeightCommand(5));
        addSequential(new QuisitorCloseCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(40, -0.4));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        addParallel(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT - 12));
        addSequential(new QuisitorMoveSpeedCommand(-0.4), 0.5);

    }
}
