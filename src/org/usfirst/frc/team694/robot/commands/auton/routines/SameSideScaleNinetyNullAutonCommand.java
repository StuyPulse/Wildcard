package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
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
        addParallel(new LiftMoveToHeightCommand(5.0));
        //TODO: Change to the right distance for driving straight into the null zone 
        addSequential(new DriveStraightWithRampingCommand(0));
        //TODO: Change the timeout of the Rotate Command
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight? -90 : 90),0);
        //TODO: Check if the move height is correct
        addSequential(new LiftMoveToHeightCommand(86));
        //TODO: Check if this is necessary
        addSequential(new DrivetrainMoveInchesEncoderCommand(5, 0.5), 1);
        addSequential(new QuisitorDeacquireCommand(), 0.5);
        //TODO: Check if this is necessary
        addSequential(new DrivetrainMoveInchesEncoderCommand(-5, 0.5), 1);
        addSequential(new LiftMoveToBottomCommand());
    }
}
