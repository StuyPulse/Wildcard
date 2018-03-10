package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;

import edu.wpi.first.wpilibj.command.PrintCommand;

public class SwitchPostScoreGetBackToStartCommand extends AutonCommandGroup {

    // 114: 2x speed
    private static final double DISTANCE_TOTAL = 153;
    private final static double INITIAL_DRIVE_RAMP_TIMEOUT = 3;

    public SwitchPostScoreGetBackToStartCommand(boolean isRight) {
        super();
        addSequential(new PrintCommand("[RightSideSwitchAuton] start!"));

        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL, RampMode.NO_RAMPING);
        driveCommand.addSpeedChange(0, -1);

        driveCommand.addTurn(DISTANCE_TOTAL - 65, isRight ? -5 : 5);
        driveCommand.addTurn(DISTANCE_TOTAL, isRight ? 70 : -80);

        addSequential(driveCommand, INITIAL_DRIVE_RAMP_TIMEOUT);
    }

}