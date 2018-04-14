package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.FasterSingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * UNFINISHED
 */
public class DoubleCubeSwitchThenGrabAnotherAutonCommand extends CommandGroup {
    private static final double SWITCH_READY_ANGLE = 45;
    private static final double SWITCH_READY_DISTANCE = 24 + 10/*24*/;

    private static final double SWITCH_SCORE_DISTANCE = 50;

    public DoubleCubeSwitchThenGrabAnotherAutonCommand(boolean isSwitchRight) {

        // Score the 1st bad boy and Grab the 2nd bad boy
        addSequential(new FasterSingleCubeSwitchAutonChooserCommand());
        addSequential(new FasterPostSingleScoreSwitchGrabCubeAutonCommand(isSwitchRight));

        // Approach the switch to score that bad boy
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isSwitchRight ? SWITCH_READY_ANGLE : (-1 * SWITCH_READY_ANGLE)), .8/*1*/);
        addParallel(new LiftMoveToHeightCommand(30.0));
        addSequential(new DrivetrainMoveInchesEncoderCommand(SWITCH_READY_DISTANCE, 1));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0), .8);

        // Score that 2nd bad boy
        addSequential(new DrivetrainMoveInchesEncoderCommand(SWITCH_SCORE_DISTANCE, 1), 2);
        addSequential(new QuisitorDeacquireCommand(), 0.5);
    }
}
