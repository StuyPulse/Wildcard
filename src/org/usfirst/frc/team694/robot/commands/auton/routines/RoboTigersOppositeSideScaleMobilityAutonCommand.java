package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RoboTigersOppositeSideScaleMobilityAutonCommand extends CommandGroup {

    public RoboTigersOppositeSideScaleMobilityAutonCommand(boolean isRight) {
        addSequential(new DriveStraightRampDownOnlyCommand(221), 3);

        // Rotate so that we're BACKWARDS
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? 90 : -90), 2);

        addSequential(new LiftMoveToHeightCommand(10));

        addSequential(new DriveStraightRampDownOnlyCommand(-90), 3);
        addParallel(new QuisitorAcquireCommand(), 0.5);

    }
}
