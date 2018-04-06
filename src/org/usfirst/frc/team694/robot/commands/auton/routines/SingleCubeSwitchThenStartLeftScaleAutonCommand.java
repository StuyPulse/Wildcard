package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class SingleCubeSwitchThenStartLeftScaleAutonCommand extends CommandGroup {
    private static final double DISTANCE_TO_POWER_CUBES = 53;

    public SingleCubeSwitchThenStartLeftScaleAutonCommand(boolean isSwitchRight) {
//        DrivetrainDriveCurveCommand curveToPowerCube = new DrivetrainDriveCurveCommand(DISTANCE_TO_POWER_CUBES);
//        curveToPowerCube.addSpeedChange(0.0, -0.6);
//        curveToPowerCube.addTurn(42.0, isSwitchRight ? 90.0 : -90.0);
//        curveToPowerCube.addTurn(52.0, 0.0);

        DrivetrainDriveCurveCommand curveToScale = new DrivetrainDriveCurveCommand(200);
        curveToScale.addSpeedChange(0, 0.6);
        curveToScale.addTurn(72, 0);
//
//        //scores switch
       addSequential(new SingleCubeSwitchAutonCommand(false));// remove whhats behind me SingleCubeSwitchAutonChooserCommand());
       addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isSwitchRight? 45 : -45));
       addParallel(new LiftMoveToBottomCommand());
       addSequential(new DrivetrainMoveInchesEncoderCommand(55 - 10 - 3, -0.4));
       addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));

       addSequential(new QuisitorOpenCommand());
       addParallel(new QuisitorAcquireCommand());
       addSequential(new DrivetrainMoveInchesEncoderCommand(30 + 5, 0.3));
       addSequential(new QuisitorCloseCommand());
       addParallel(new QuisitorAcquireCommand());
       addSequential(new WaitCommand(0.5));
       addParallel(new QuisitorStopCommand());
       addSequential(new DrivetrainMoveInchesEncoderCommand(30, -0.5));
       addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-45));
       addSequential(new DrivetrainMoveInchesEncoderCommand(24, 0.5));



    }
}
