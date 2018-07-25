package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.WaitUntilCubeDetectedCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
class PostSingleScoreSwitchGrabCubeAutonCommand extends CommandGroup {

    // Set to false for now
    private static final boolean FAST_BUT_UNCERTAIN_SCORE = false;

    // FOR THIRD CUBE:
    // true:  Grab middle 2nd layer cube in pyramid
    // false: Grab corner 1st layer cube in pyramid
    private static final boolean CUBE_3_GRAB_MIDDLE_CUBE = false;

    // isThirdCube
    // true:  Third Cube
    // false: Second Cube
    public PostSingleScoreSwitchGrabCubeAutonCommand(boolean isSwitchRight, boolean isThirdCube) {

        // Forced here for 3rd cube.
        // Grab the diagonal if we want to
        if (isThirdCube && !CUBE_3_GRAB_MIDDLE_CUBE) {
            // Get into diagonal grabbing orientation
            addParallel(new LiftMoveToBottomCommand(), 2);
//            addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 15));
//            addSequential(new DrivetrainMoveInchesEncoderCommand(28 / 2, -0.4));
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isSwitchRight? -45 : 45), 1);

            // Move forward and grab 3rd cube diagonally
            addSequential(new QuisitorOpenCommand());
            addParallel(new QuisitorAcquireCommand(), 3);

            addParallel(new DrivetrainMoveInchesEncoderCommand(28*1.4 + 3, 0.4));
            addSequential(new WaitUntilCubeDetectedCommand());
            addSequential(new DrivetrainStopCommand());
            addSequential(new QuisitorCloseCommand());
            addParallel(new QuisitorAcquireCommand()); // Keep acquiring (don't interrupt)

            addSequential(new WaitCommand(0.75));

            // Move back and face switch, but don't approach to score
            addSequential(new DrivetrainMoveInchesEncoderCommand(28*1.4, -0.4));
            addParallel(new QuisitorAcquireCommand(), 0.5);
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
            return;
        }

        if (FAST_BUT_UNCERTAIN_SCORE) {
            // Don't use this. It's actually not much faster.
            if (!isThirdCube) {
                addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 15));
            }

            DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(-1 * (45 + 26 + 13 + 5));

            driveCommand.addSpeedChange(0, 1.5);
            driveCommand.addTurn(0, isSwitchRight ? 90.0 : -90.0);
            driveCommand.addTurn(30 + 26 + 13 + 5, 0.0);
            addSequential(driveCommand);
        } else {

            // Get in position to grab second cube
            double GRAB_READY_ANGLE = 45 + 15;
            double GRAB_READY_DISTANCE = 55 - 10 - 3 + 4 - 12 + 10 + (isSwitchRight? 0 : 8.4);

            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(
                    isSwitchRight ? GRAB_READY_ANGLE : -1 * GRAB_READY_ANGLE), 1);
            if (isThirdCube) {
                addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_READY_DISTANCE, -0.4 - .1));
            }
            else {
                addParallel(new LiftMoveToBottomCommand(), 2);
                addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_READY_DISTANCE, -0.4 - .1));
            }
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0), 1);

        }

        // Grab the second cube
        double GRAB_FORWARD_DISTANCE = 30 + 5;
        double GRAB_BACK_DISTANCE = 30 - (isSwitchRight ? 0 : 15);

        addSequential(new QuisitorOpenCommand());
        if (isThirdCube) {
            addSequential(new LiftMoveToHeightCommand(15));
        }
        addParallel(new QuisitorAcquireCommand());

        addParallel(new DrivetrainMoveInchesEncoderCommand(isThirdCube ? (GRAB_FORWARD_DISTANCE + 13) : GRAB_FORWARD_DISTANCE, 0.3/*+.2*/), 1.5);
        addSequential(new WaitUntilCubeDetectedCommand());
        addSequential(new DrivetrainStopCommand());

        addSequential(new QuisitorCloseCommand());
        addParallel(new QuisitorAcquireCommand());
        addSequential(new WaitCommand(0.5));
        addParallel(new QuisitorStopCommand());
        DrivetrainDriveCurveCommand backupCommand = new DrivetrainDriveCurveCommand(GRAB_BACK_DISTANCE, RampMode.NO_RAMPING);
        //addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_BACK_DISTANCE, -0.5));
        backupCommand.addTurn(0, 0);
        backupCommand.addSpeedChange(0, -0.5);
        addSequential(backupCommand);

//        // Get in 2nd cube Switch scoring position
//        double SCALE_READY_ANGLE = 45;
//        double SCALE_READY_DISTANCE = 24 + 24;
//
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(SCALE_READY_ANGLE), 1);
//        addParallel(new LiftMoveToHeightCommand(30.0)); //TODO: Find a height for the lift to move to.
//        addSequential(new DrivetrainMoveInchesEncoderCommand(SCALE_READY_DISTANCE, 1));
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
//
//        // Score that 2nd bad boy
//        double SCALE_SCORE_DISTANCE = 20;
//        addSequential(new DrivetrainMoveInchesEncoderCommand(SCALE_SCORE_DISTANCE, 1), 2);
//        addSequential(new QuisitorDeacquireCommand(), 0.5);
        

        // Old routine, not sure if it works

//        DrivetrainDriveCurveCommand curveToCube = new DrivetrainDriveCurveCommand(DISTANCE_TO_CUBE);
//        curveToCube.addSpeedChange(0, -0.6);
//        curveToCube.addTurn(40, 90);
//
//        DrivetrainDriveCurveCommand curveToSwitch = new DrivetrainDriveCurveCommand(DISTANCE_TO_SWITCH);
//        curveToSwitch.addSpeedChange(0, 0.6);
//        curveToSwitch.addTurn(40, 0);
//
//        addSequential(new SingleCubeSwitchAutonChooserCommand());
//        addSequential(curveToSwitch, 5); //TODO: Is this the right amt of secs?
//        addParallel(new LiftMoveToBottomCommand());
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
//        addSequential(new QuisitorOpenCommand());
//        addParallel(new QuisitorAcquireCommand(),2);
//        addSequential(new DrivetrainMoveInchesEncoderCommand(20, 0.1));
//        addSequential(new QuisitorCloseCommand());
//        addSequential(new DrivetrainMoveInchesEncoderCommand(20, -0.1));
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(90));
//        addSequential(new LiftMoveToHeightCommand(10));
//        addSequential(curveToCube, 5); 
//        addSequential(new QuisitorDeacquireCommand());
    }
}
