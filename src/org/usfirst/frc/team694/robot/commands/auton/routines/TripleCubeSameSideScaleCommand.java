package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * WITH CURVES
 */
public class TripleCubeSameSideScaleCommand extends CommandGroup {
    private static final double DISTANCE_TOTAL = 130 + 116 + 40;//296 - 4;

    private static final boolean IS_2ND_SCORE_FAST_AND_CRAZY = false;

    public TripleCubeSameSideScaleCommand(boolean isRight) {

        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL);
        driveCommand.addSpeedChange(0, 0.75);
        driveCommand.addTurn(130 - 35, isRight ? -(45/2) : (45/2));
//        driveCommand.addTurn(130 + 116, isRight ? 5 : -5);

        // Curve to the scale + ready to score
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(86), 15));
        addSequential(driveCommand, 3.3);

        // Spit out cube
        addParallel(new QuisitorMoveSpeedCommand(-0.7), 0.5);

        // Go down and grab 2nd cube
        if (IS_2ND_SCORE_FAST_AND_CRAZY) {
            // K turn / Drift
            DrivetrainDriveCurveCommand kTurnCommand = new DrivetrainDriveCurveCommand(-20, RampMode.NO_RAMPING);
            kTurnCommand.addSpeedChange(0, 0.4);
            kTurnCommand.addTurn(0, 15);
            kTurnCommand.addTurn(10, 180);
            kTurnCommand.addSpeedChange(15, -0.4);
            addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 5));
            addSequential(kTurnCommand);
        } else {
            addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 5));
            addSequential(new DrivetrainMoveInchesEncoderCommand(-15, -0.4));
            addSequential(new LiftMoveToBottomCommand());
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -150 : 150));
        }
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand(), 2);
        addSequential(new DrivetrainMoveInchesEncoderCommand(/*60 - 10*/24 + 12 , 0.3));
//        addSequential(new DriveStraightRampDownOnlyCommand(60 - 10));
        addSequential(new QuisitorCloseCommand());

        // Get ready to score a 2nd time
        if (IS_2ND_SCORE_FAST_AND_CRAZY) {
            // K turn / Drift
            DrivetrainDriveCurveCommand kTurnCommand = new DrivetrainDriveCurveCommand(-30, RampMode.NO_RAMPING);
            kTurnCommand.addSpeedChange(0, 0.75);
            kTurnCommand.addTurn(15, 0);
            addSequential(kTurnCommand);
        } else {
            addParallel(new QuisitorAcquireCommand(), 1);
            addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(86), 10));
            addSequential(new DrivetrainMoveInchesEncoderCommand(-24, -0.3));
            addParallel(new QuisitorAcquireCommand(), 1);
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -(45/2) : (45/2)));
//            addParallel(new LiftMoveToHeightCommand(68));
            addSequential(new DrivetrainMoveInchesEncoderCommand(/*62 - 20*/10+3, 0.5));
        }

        // Deacquire 2nd cube
        addSequential(new QuisitorMoveSpeedCommand(-0.5), 1);

        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 15));
        addSequential(new DrivetrainMoveInchesEncoderCommand(15+3, -0.4));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -135 : 135));
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand(), 2);
        addSequential(new DrivetrainMoveInchesEncoderCommand(26 + 6, 0.3));
//        addSequential(new DriveStraightRampDownOnlyCommand(40));
        addSequential(new QuisitorCloseCommand());
        addParallel(new QuisitorAcquireCommand(), 1);
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(5), 30));
        addSequential(new DrivetrainMoveInchesEncoderCommand(40, -0.4));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -(45/2) : (45/2)));
        addParallel(new QuisitorAcquireCommand(), 0.5);
        addSequential(new LiftMoveToHeightCommand(86));
        addSequential(new QuisitorMoveSpeedCommand(-0.4), 0.5);

    }
}
