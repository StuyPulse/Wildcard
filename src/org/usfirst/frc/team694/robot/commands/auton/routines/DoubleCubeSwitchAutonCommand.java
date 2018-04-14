package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.FasterSingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleCubeSwitchAutonCommand extends CommandGroup {
    private static final double DISTANCE_TO_CUBE = 100;
    private static final double DISTANCE_TO_SWITCH = 100;

    private static final double SWITCH_READY_ANGLE = 45;
    private static final double SWITCH_READY_DISTANCE = 24 + 10/*24*/;

    private static final double SCALE_SCORE_DISTANCE = 20;

    public DoubleCubeSwitchAutonCommand(boolean isSwitchRight) {

        addSequential(new FasterSingleCubeSwitchAutonChooserCommand());

        addSequential(new FasterPostSingleScoreSwitchGrabCubeAutonCommand(isSwitchRight));

        // Get in 2nd cube Switch scoring position

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(
                isSwitchRight ? SWITCH_READY_ANGLE : (-1 * SWITCH_READY_ANGLE)), .8/*1*/);
        addParallel(new LiftMoveToHeightCommand(30.0)); //TODO: Find a height for the lift to move to.
        addSequential(new DrivetrainMoveInchesEncoderCommand(SWITCH_READY_DISTANCE, 1));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0), 1);

        // Score that 2nd bad boy
        addSequential(new DrivetrainMoveInchesEncoderCommand(SCALE_SCORE_DISTANCE, 1), 2);
        // Don't actually score. It makes no difference
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
