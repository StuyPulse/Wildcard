package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DoubleCubeSwitchAutonCommand extends CommandGroup {
    private static final double DISTANCE_TO_CUBE = 100;
    private static final double DISTANCE_TO_SWITCH = 100;

    public DoubleCubeSwitchAutonCommand(boolean isSwitchRight) {

        addSequential(new SingleCubeSwitchAutonChooserCommand());

        addSequential(new PostSingleScoreSwitchGrabCubeAutonCommand(isSwitchRight));

        // Get in 2nd cube Switch scoring position
        double SCALE_READY_ANGLE = 45;
        double SCALE_READY_DISTANCE = 24 + 24;

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isSwitchRight ? SCALE_READY_ANGLE : (-1 * SCALE_READY_ANGLE)), 1);
        addParallel(new LiftMoveToHeightCommand(30.0)); //TODO: Find a height for the lift to move to.
        addSequential(new DrivetrainMoveInchesEncoderCommand(SCALE_READY_DISTANCE, 1));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0), 1);

        // Score that 2nd bad boy
        double SCALE_SCORE_DISTANCE = 20;
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
