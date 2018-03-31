package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TripleCubeSameSideSwitchAutonCommand extends CommandGroup {

    public TripleCubeSameSideSwitchAutonCommand() {
        //Drive back 48 inches at -25% and lower lift to the bottom.
        addParallel(new LiftMoveToBottomCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(48, -0.25));
        //Rotate to -45 degrees and start acquiring (3 seconds)
        addParallel(new DrivetrainRotateAbsoluteDegreesPIDCommand(-45));
        addSequential(new QuisitorAcquireCommand());
        //Drive 60 inches at 25%
        addSequential(new DrivetrainMoveInchesEncoderCommand(60, 0.25));
        addSequential(new DrivetrainMoveInchesEncoderCommand(-60, -0.25));  
        //Rotate to Absolute Zero Degrees.
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        //Raise lift to 20 inches.
        addSequential(new LiftMoveToHeightCommand(20));
        //Drive 48 inches:
        addSequential(new DrivetrainMoveInchesEncoderCommand(48, 1));
        //Deacquire for 1 second.
        addSequential(new QuisitorDeacquireCommand(), 1);
    }
}
