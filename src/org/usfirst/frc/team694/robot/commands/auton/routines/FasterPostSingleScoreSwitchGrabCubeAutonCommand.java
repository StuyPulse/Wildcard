package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FasterPostSingleScoreSwitchGrabCubeAutonCommand extends CommandGroup {

    public FasterPostSingleScoreSwitchGrabCubeAutonCommand(boolean isSwitchRight) {
     // Get in position to grab second cube
        double GRAB_READY_ANGLE = 45;
        double GRAB_READY_DISTANCE = 55 - 10 - 3;

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(
                isSwitchRight ? GRAB_READY_ANGLE : -1 * GRAB_READY_ANGLE), .8/*1*/);
        addParallel(new LiftMoveToBottomCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_READY_DISTANCE, -0.4));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0), .8/*1*/);

        // Grab the second cube
        double GRAB_FORWARD_DISTANCE = 30 /*+ 5*/;
        double GRAB_BACK_DISTANCE = /*30*/ 30 - 5;

        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_FORWARD_DISTANCE, .75/*0.3*/));
        addSequential(new QuisitorCloseCommand());
        addParallel(new QuisitorAcquireCommand());
        addSequential(new WaitCommand(0.5));
        addParallel(new QuisitorStopCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_BACK_DISTANCE, -0.8/*-0.5*/));
    }
}
