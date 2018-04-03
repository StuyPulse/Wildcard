package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class SingleCubeDifferentSideScaleAutonCommand extends CommandGroup {

    public SingleCubeDifferentSideScaleAutonCommand(boolean isRight) {
        addSequential(new PrintCommand("[SingleCubeDifferentSideScale] isRight? " + isRight));
        // Add Commands here:
        //TODO: kill magic numbers
        addSequential(new DriveStraightRampDownOnlyCommand(235 - 14), 3);

        // Rotate so that we're BACKWARDS
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? 90 : -90), 2);

        addSequential(new LiftMoveToHeightCommand(5));      

        addSequential(new DriveStraightRampDownOnlyCommand(-1 * (234 + 20 + 24 + 24 + 10) ),3);
        addParallel(new QuisitorAcquireCommand(), 0.5);

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? 35 : -35), 1.5 );//TODO: change to 45

        // Drive forward and raise lift for scoring
        addParallel(new DriveStraightPIDCommand(18, 0.3));
        addSequential(new LiftMoveToHeightCommand(86), 2.5);

        addSequential(new QuisitorDeacquireCommand(), 0.5);//TODO: ask for timeout

        // Back up
        addSequential(new DriveStraightPIDCommand(18, -0.3));
        addSequential(new LiftMoveToBottomCommand());
        addParallel(new QuisitorOpenCommand());
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? 150.0 - 5 - 13 : -150.0 + 5 + 13), 3.0);
        addSequential(new DrivetrainMoveInchesEncoderCommand(30-12, .6), 3.51);
    }
}
