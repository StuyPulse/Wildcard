package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SingleCubeSwitchThenStartRightScaleAutonCommand extends CommandGroup {
//    private static final double DISTANCE_TO_POWER_CUBES = 53;

    public SingleCubeSwitchThenStartRightScaleAutonCommand(boolean isSwitchRight) {

        // Score 1st cube Switch
        addSequential(new SingleCubeSwitchAutonChooserCommand());

        // Grab 2nd cube
        addSequential(new PostSingleScoreSwitchGrabCubeAutonCommand(isSwitchRight,false));

        // Get in scale scoring position
        double SCALE_READY_ANGLE = 45 + 10;
        double SCALE_READY_DISTANCE = 24 + 24;

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(SCALE_READY_ANGLE));
        addSequential(new DrivetrainMoveInchesEncoderCommand(SCALE_READY_DISTANCE, 1));
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));

        
        //        DrivetrainDriveCurveCommand curveToPowerCube = new DrivetrainDriveCurveCommand(DISTANCE_TO_POWER_CUBES);
        //        curveToPowerCube.addSpeedChange(0.0, -0.6);
        //        curveToPowerCube.addTurn(42.0, isSwitchRight ? 90.0 : -90.0);
        //        curveToPowerCube.addTurn(52.0, 0.0);

//        DrivetrainDriveCurveCommand curveToScale = new DrivetrainDriveCurveCommand(200);
//        curveToScale.addSpeedChange(0, 0.6);
//        curveToScale.addTurn(72, 0);


        //        addSequential(curveToPowerCube, 5); //TODO: Is this the right amt of secs?

        //        addSequential(new DrivetrainMoveInchesEncoderCommand(10, -0.3));

        // Get in position to grab second cube
//        double GRAB_READY_ANGLE = 45;
//        double GRAB_READY_DISTANCE = 55 - 10 - 3;
//
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(
//                isSwitchRight ? GRAB_READY_ANGLE : -1 * GRAB_READY_ANGLE));
//        addParallel(new LiftMoveToBottomCommand());
//        addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_READY_DISTANCE, -0.4));
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
//
//        // Grab the second cube
//        double GRAB_FORWARD_DISTANCE = 30 + 5;
//        double GRAB_BACK_DISTANCE = 30;
//
//        addSequential(new QuisitorOpenCommand());
//        addParallel(new QuisitorAcquireCommand());
//        addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_FORWARD_DISTANCE, 0.3));
//        addSequential(new QuisitorCloseCommand());
//        addParallel(new QuisitorAcquireCommand());
//        addSequential(new WaitCommand(0.5));
//        addParallel(new QuisitorStopCommand());
//        addSequential(new DrivetrainMoveInchesEncoderCommand(GRAB_BACK_DISTANCE, -0.5));

        // Grab 90 degrees
        //        addSequential(new DrivetrainMoveInchesEncoderCommand(10, -0.3));
        //        addParallel(new LiftMoveToBottomCommand());
        //        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-90));
        //        addSequential(new QuisitorOpenCommand());
        //        addParallel(new QuisitorAcquireCommand());
        //        addSequential(new DrivetrainMoveInchesEncoderCommand(70, 0.4));
        //        addSequential(new QuisitorCloseCommand());
        //        addParallel(new QuisitorAcquireCommand());
        //        addSequential(new WaitCommand(0.5));
        //        addSequential(new LiftMoveToHeightCommand(5));
        //        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-270));

        //        addParallel(new LiftMoveToBottomCommand());
        //        addSequential(new QuisitorOpenCommand());
        //        addParallel(new QuisitorAcquireCommand(), 2);
        //        addSequential(new DrivetrainMoveInchesEncoderCommand(24, 0.4));
        //        addSequential(new QuisitorCloseCommand());
        //        addSequential(new DrivetrainMoveInchesEncoderCommand(15, -0.25));

        //        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-90));
        //        addSequential(curveToScale);

        // TODO: Add me back
        //score scale
        //        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-60));
        //        addSequential(new LiftMoveToHeightCommand(86 - 12));
        //        addSequential(new QuisitorDeacquireCommand(), 0.5);
        //
        //        //grab cube and backup
        //        addSequential(new LiftMoveToBottomCommand());
        //        addSequential(new DrivetrainMoveInchesEncoderCommand(50, -1));
        //        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-150));
        //        addParallel(new QuisitorAcquireCommand(), 2);
        //        addSequential(new DrivetrainMoveInchesEncoderCommand(50, 1));
        //        addSequential(new DrivetrainMoveInchesEncoderCommand(24, -1));
    }
}
