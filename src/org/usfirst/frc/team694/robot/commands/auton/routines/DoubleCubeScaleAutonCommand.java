 package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeScaleAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleCubeScaleAutonCommand extends CommandGroup {

    public DoubleCubeScaleAutonCommand(boolean isRight) {
        // Score the first cube
        addSequential(new SingleCubeScaleAutonChooserCommand());

        // Grab the second cube
        double GRAB_APPROACH_ANGLE = 145;
        double GRAB_APPROACH_DISTANCE = 83;
        double GRAB_BACKUP_DISTANCE = 44;

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand( (isRight ? -1 : 1) * GRAB_APPROACH_ANGLE), 1);
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand());
        addSequential(new DriveStraightRampDownOnlyCommand(GRAB_APPROACH_DISTANCE), 2);
        //addSequential(new DrivetrainMoveInchesEncoderCommand(64.0 + 7, .6), 2 + .5);
        addSequential(new QuisitorCloseCommand());
        addSequential(new QuisitorAcquireCommand(), .5);
        addParallel(new QuisitorAcquireCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_BACKUP_DISTANCE, -1), 2.5);

        // Move back while lifting to give cube clearance before scoring
        double SCORE_ANGLE = 85;
        double SCORE_BACKUP_DISTANCE = 25;
        double SCORE_BACKUP_SPEED = 0.4;

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand((isRight ? -1 : 1) * SCORE_ANGLE ), .8);
        addSequential(new QuisitorStopCommand());
        addParallel(new DrivetrainMoveInchesEncoderCommand(SCORE_BACKUP_DISTANCE, -1 * SCORE_BACKUP_SPEED));
        addSequential(new LiftMoveToHeightCommand(83.0));

        // Score, maybe while moving
        addSequential(new QuisitorDeacquireCommand(), 1.5);
        addSequential(new LiftMoveToBottomCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(SCORE_BACKUP_DISTANCE, SCORE_BACKUP_SPEED));
    }
}
