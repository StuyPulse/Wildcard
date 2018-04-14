package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
class PostSingleScoreSwitchGrabCubeAutonCommand extends CommandGroup {

    public PostSingleScoreSwitchGrabCubeAutonCommand(boolean isSwitchRight, boolean isSecondCube) {

        // Get in position to grab second cube
        double GRAB_READY_ANGLE = 45;
        double GRAB_READY_DISTANCE = 55 - 10 - 3 + 16;
        
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(
                isSwitchRight ? GRAB_READY_ANGLE : -1 * GRAB_READY_ANGLE), 1);
        addParallel(new LiftMoveToBottomCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_READY_DISTANCE, -0.4));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0), 1);

        // Grab the second cube
        double GRAB_FORWARD_DISTANCE = 30 + 5;
        double GRAB_BACK_DISTANCE = 30;

        addSequential(new QuisitorOpenCommand());
        if (isSecondCube) {
            addSequential(new LiftMoveToHeightCommand(15));
        }
        addParallel(new QuisitorAcquireCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_FORWARD_DISTANCE, 0.3));
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
