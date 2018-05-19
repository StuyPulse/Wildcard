package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class SameSideScaleNinetyNullAutonCommand extends CommandGroup {

    public SameSideScaleNinetyNullAutonCommand(boolean isRight) {
        addSequential(new PrintCommand("[SingleCubeSameSideScale] isRight? " + isRight));
        //TODO: Check if the move height is correct
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(5.0), 30));
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(10.0), 250));
        //TODO: Change to the right distance for driving straight into the null zone 
        addSequential(new DriveStraightWithRampingCommand(300 + 5 + 24 + 10), 4);
        //TODO: Change the timeout of the Rotate Command
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight? -(80+10 + 45) : (80+10 + 45)), 2);
        //TODO: Check if the move height is correct
        //addSequential(new DrivetrainMoveInchesEncoderCommand(10 - 5, -0.5));
        addSequential(new DrivetrainMoveInchesEncoderCommand(5, -.3));
        addSequential(new LiftMoveToHeightCommand(86));
        addSequential(new DrivetrainMoveInchesEncoderCommand(5, .3));
        //TODO: Check if this is necessary
        //addSequential(new DrivetrainMoveInchesEncoderCommand(5, 0.5), .5);
        addSequential(new QuisitorMoveSpeedCommand(-(1 - 0.5)), 0.5 + 0.5);
        addSequential(new DrivetrainMoveInchesEncoderCommand(5, -.3));
        //TODO: Check if this is necessary
//        addSequential(new DrivetrainMoveInchesEncoderCommand(5, -0.5), .5);
        addSequential(new LiftMoveToBottomCommand());
    }
}
