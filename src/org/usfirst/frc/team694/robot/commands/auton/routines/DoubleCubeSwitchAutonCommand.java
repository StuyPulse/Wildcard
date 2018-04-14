package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DoubleCubeSwitchAutonCommand extends CommandGroup {
    private static final double DISTANCE_TO_CUBE = 100;
    private static final double DISTANCE_TO_SWITCH = 100;

    private static final double SWITCH_READY_ANGLE = 45;
    private static final double SWITCH_READY_DISTANCE = 24 + 10/*24*/;

    private static final double SWITCH_SCORE_DISTANCE = 20 + 6 + 13 + 5;

    public DoubleCubeSwitchAutonCommand(boolean isSwitchRight) {

        addSequential(new SingleCubeSwitchAutonChooserCommand());

        addSequential(new PostSingleScoreSwitchGrabCubeAutonCommand(isSwitchRight, false));

        // Get in 2nd cube Switch scoring position

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(
                isSwitchRight ? SWITCH_READY_ANGLE : (-1 * SWITCH_READY_ANGLE)), .8/*1*/);
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30.0), 15));
        addSequential(new DrivetrainMoveInchesEncoderCommand(SWITCH_READY_DISTANCE, 1));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0), 1);

        // Score that 2nd bad boy
        //addSequential(new DrivetrainMoveInchesEncoderCommand(SWITCH_SCORE_DISTANCE, 1), 2);
        addSequential(new DrivetrainDriveCurveCommand(SWITCH_SCORE_DISTANCE, RampMode.NO_RAMP_UP));
        //addSequential(new WaitCommand(0.5));
        addSequential(new QuisitorDeacquireCommand(), 0.5);
        addSequential(new DrivetrainMoveInchesEncoderCommand(10 + 5, -1)); // -0.45


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
