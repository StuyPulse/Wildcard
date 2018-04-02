package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
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
        addSequential(new DriveStraightRampDownOnlyCommand(235 - 14));

        // Rotate so that we're BACKWARDS
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? 90 : -90));

        addSequential(new LiftMoveToHeightCommand(5));      

        addSequential(new DriveStraightRampDownOnlyCommand(-1 * (234 + 20 + 24 + 24) ));

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? 45 : -45) );

        addParallel(new LiftMoveToHeightCommand(75));//TODO: Ask engineering
        addSequential(new DriveStraightPIDCommand(55, 0.3));

        addSequential(new QuisitorDeacquireCommand(), 2);//TODO: ask for timeout
    }
}
