package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SingleCubeSwitchThenStartLeftScaleAutonCommand extends CommandGroup {
    private static final double DISTANCE_TO_POWER_CUBES = 53;

    public SingleCubeSwitchThenStartLeftScaleAutonCommand(boolean isRight) {
        DrivetrainDriveCurveCommand curveToPowerCube = new DrivetrainDriveCurveCommand(DISTANCE_TO_POWER_CUBES);
        curveToPowerCube.addSpeedChange(0.0, -0.6);
        curveToPowerCube.addTurn(42.0, isRight ? 90.0 : -90.0);
        curveToPowerCube.addTurn(52.0, 0.0);

        DrivetrainDriveCurveCommand curveToScale = new DrivetrainDriveCurveCommand(200);
        curveToScale.addSpeedChange(0, 0.6);
        curveToScale.addTurn(72, 0);

        //scores switch
        addSequential(new SingleCubeSwitchAutonChooserCommand());

        addSequential(curveToPowerCube, 5); //TODO: Is this the right amt of secs?
        addParallel(new LiftMoveToBottomCommand());
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand(), 2);
        addSequential(new DrivetrainMoveInchesEncoderCommand(24, 0.4));
        addSequential(new QuisitorCloseCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(15, -0.25));

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(90));
        addSequential(curveToScale);
    }
}
