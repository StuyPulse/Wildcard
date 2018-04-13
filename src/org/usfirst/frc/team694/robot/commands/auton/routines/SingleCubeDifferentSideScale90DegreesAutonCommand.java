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
public class SingleCubeDifferentSideScale90DegreesAutonCommand extends CommandGroup {

    public SingleCubeDifferentSideScale90DegreesAutonCommand(boolean isRight) {
            addSequential(new PrintCommand("[SingleCubeDifferentSideScale90DegreesAutonCommand] isRight? " + isRight));
            // Add Commands here:
            addSequential(new DriveStraightRampDownOnlyCommand(235 - 14), 3);
            // Rotate so that we're BACKWARDS.
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? 90 : -90), 2);
            addSequential(new LiftMoveToHeightCommand(5));      
            addSequential(new DriveStraightRampDownOnlyCommand(-1 * (197) ),3);
            //Rotate 90 Degrees so we face the Scale.
            addParallel(new QuisitorAcquireCommand(), 0.5);
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? 90 : -90), 1.5 );
            //Go forward towards the scale and raising the lift.
            addParallel(new DriveStraightRampDownOnlyCommand(25),1);
            addSequential(new LiftMoveToHeightCommand(83.0));
            addSequential(new QuisitorDeacquireCommand(), 1.5);
            addSequential(new LiftMoveToBottomCommand());
    }
}
