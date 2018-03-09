package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.BITCOINCommand;
import org.usfirst.frc.team694.robot.commands.CrabArrowAcquireCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleGrabCubeAfterScoringCommand extends CommandGroup {

    public ScaleGrabCubeAfterScoringCommand(boolean isRightSide) {
        addSequential(new DrivetrainMoveInchesEncoderCommand(-0.3, 10));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        addSequential(new DrivetrainMoveInchesEncoderCommand(-0.3, 10));
        addSequential(new LiftMoveToBottomCommand());
        // Relative degrees: +/- 165
        if (isRightSide) {
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-165));
        } else {
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(165));
        }
        addParallel(new CrabArrowAcquireCommand(), 2);
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.4, 20), 2);
        addSequential(new BITCOINCommand());
    }
}
