package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.commands.CrabArrowAcquireCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleGrabCubeAfterScoringCommand extends CommandGroup {

    public ScaleGrabCubeAfterScoringCommand(boolean isRightSide) {
        addSequential(new DrivetrainMoveInchesEncoderCommand(-0.3, 20));
        // Relative degrees: +/- 165
        if (isRightSide) {
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-165));
        } else {
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(165));
        }
        addParallel(new LiftMoveToBottomCommand());
        addParallel(new CrabArrowAcquireCommand(), 5);
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.3, 20), 5);
    }
}
