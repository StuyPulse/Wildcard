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
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.WaitUntilCubeDetectedCommand;
import org.usfirst.frc.team694.robot.commands.auton.WaitUntilLiftGoesBelowHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * WITH CURVES
 */
public class TripleCubeSameSideScaleCommand extends CommandGroup {
    private static final double DISTANCE_TOTAL = 130 + 116 + 40;//296 - 4;

    public TripleCubeSameSideScaleCommand(boolean isRight) {

        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL);
        driveCommand.addSpeedChange(0, 0.75);
        driveCommand.addTurn(130 - 35, isRight ? -(45/2 - 5 - 5 + 5) : (45/2 - 5 - 5 + 5));
//        driveCommand.addTurn(130 + 116, isRight ? 5 : -5);

        // Curve to the scale + ready to score
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(86), 15));
        
        addSequential(driveCommand, 3.3);

        // Spit out cube
        addParallel(new QuisitorMoveSpeedCommand(-0.7), 0.5);

        // Go down and grab 2nd cube

        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 5));
        addSequential(new DrivetrainMoveInchesEncoderCommand(-15, -0.4));
        // Don't wait until lift hits the bottom before rotating
//            addSequential(new LiftMoveToBottomCommand());
        addSequential(new WaitUntilLiftGoesBelowHeightCommand(10));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -150 : 150), 1.25);

        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand(), 2);

        addParallel(new DrivetrainMoveInchesEncoderCommand(/*60 - 10*/24 + 12 , 0.3 + 0.1));
        addSequential(new WaitUntilCubeDetectedCommand());
        addSequential(new DrivetrainStopCommand());
//        addSequential(new DriveStraightRampDownOnlyCommand(60 - 10));
        addSequential(new QuisitorCloseCommand());

        // Get ready to score a 2nd time
<<<<<<< HEAD
        addParallel(new QuisitorAcquireCommand(), 1);
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(86), 10));
        addSequential(new DrivetrainMoveInchesEncoderCommand(-24, -0.3 - 0.1));
        addParallel(new QuisitorAcquireCommand(), 1);
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -(45/2) : (45/2)), 1.25);
=======
        if (IS_2ND_SCORE_FAST_AND_CRAZY) {
            // K turn / Drift
            DrivetrainDriveCurveCommand kTurnCommand = new DrivetrainDriveCurveCommand(-30, RampMode.NO_RAMPING);
            kTurnCommand.addSpeedChange(0, 0.75);
            kTurnCommand.addTurn(15, 0);
            addSequential(kTurnCommand);
        } else {
            addParallel(new QuisitorAcquireCommand(), 1);
            addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(86), 10));
            addSequential(new DrivetrainMoveInchesEncoderCommand(-24, -0.3 - 0.1));
            addParallel(new QuisitorAcquireCommand(), 1);
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -(45/2 - 5) : (45/2 - 5)), 1.25);
>>>>>>> 87450592dbb1c7628cfb1dcda1bce9c96865e3ee
//            addParallel(new LiftMoveToHeightCommand(68));
        addSequential(new DrivetrainMoveInchesEncoderCommand(/*62 - 20*/10+3, 0.4));

        // Wait to stabilize
        addSequential(new WaitCommand(0.2));

        // Deacquire 2nd cube
        addParallel(new QuisitorMoveSpeedCommand(-0.5), 1);

        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 15));
        addSequential(new DrivetrainMoveInchesEncoderCommand(15+3, -0.4));
//        addSequential(new LiftMoveToBottomCommand());
        addSequential(new WaitUntilLiftGoesBelowHeightCommand(10));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -125 : 125), 1.25);

        // Prepare to grab 3rd cube
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand(), 2);

        // Approach 3rd cube to grab
        addParallel(new DrivetrainMoveInchesEncoderCommand(26 + 6 + 12 + 24, 0.3 + 0.1), 1.5);
        addSequential(new WaitUntilCubeDetectedCommand());
        addSequential(new DrivetrainStopCommand());

        // Grab the 3rd cube
        //        addSequential(new DriveStraightRampDownOnlyCommand(40));
        addSequential(new QuisitorCloseCommand());
        addParallel(new QuisitorAcquireCommand(), 1);
        //addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(5), 30));

        addSequential(new WaitCommand(0.5));
        //addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(86), 10));
        addSequential(new DrivetrainMoveInchesEncoderCommand(40, -0.4));

//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -(45/2) : (45/2)), 1.25);
//        addParallel(new QuisitorAcquireCommand(), 0.5);
//
//        addSequential(new DrivetrainMoveInchesEncoderCommand(20, 0.4));
//        // Spit out 3rd cube and dominate that scale baby
//        addSequential(new QuisitorMoveSpeedCommand(-0.4), 0.5);

    }
}
