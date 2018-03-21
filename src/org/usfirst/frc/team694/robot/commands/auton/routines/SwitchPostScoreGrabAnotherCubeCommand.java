package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.PrintCommand;

public class SwitchPostScoreGrabAnotherCubeCommand extends AutonCommandGroup {

    private static final double SWITCH_WIDTH = 189.5;
    private static final double CUBE_PYRAMID_FRONT_LENGTH = 12 * 3;

    private static final double DISTANCE_TOTAL = 103;
    private final static double INITIAL_DRIVE_RAMP_TIMEOUT = 3;

    private static final boolean IS_ACCURATE_BUT_SLOW = true;

    public SwitchPostScoreGrabAnotherCubeCommand(boolean isRight) {
        super();
        addSequential(new PrintCommand("[RightSideSwitchAuton] start!"));

        if (IS_ACCURATE_BUT_SLOW) {
            addSequential(new DrivetrainMoveInchesEncoderCommand(CUBE_PYRAMID_FRONT_LENGTH + 10, -0.4));
            addSequential(new LiftMoveToBottomCommand());
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight? -90 : 90));
            addSequential(new DriveStraightPIDCommand(SWITCH_WIDTH / 2 - 30 , 1));
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));

            // Go forth and grab the cube
            addParallel(new QuisitorAcquireCommand(), 3);
            addSequential(new DrivetrainMoveInchesEncoderCommand(10, 0.5));
        } else {
            // Drive back to the start
            DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL, RampMode.NO_RAMPING);
            driveCommand.addSpeedChange(0, -1);
            driveCommand.addTurn(DISTANCE_TOTAL - 65, isRight ? -5 : 5);
            driveCommand.addTurn(DISTANCE_TOTAL, isRight ? 70 : -80);
            addSequential(driveCommand, INITIAL_DRIVE_RAMP_TIMEOUT);

            addSequential(new LiftMoveToBottomCommand());

            // Go forth and grab the cube
            addSequential(new QuisitorOpenCommand());
            addSequential(new DrivetrainMoveInchesEncoderCommand(100, 0.5), 2);
            addSequential(new QuisitorCloseCommand());
            addSequential(new QuisitorAcquireCommand(), 3);
        }
    }

}