package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;

import edu.wpi.first.wpilibj.command.PrintCommand;

public class SwitchPostScoreDriveToScaleAutonCommand extends AutonCommandGroup {

    private static final double DISTANCE_TOTAL = 155;
    private final static double DRIVE_RAMP_TIMEOUT = 30;

    public SwitchPostScoreDriveToScaleAutonCommand(boolean isRight) {
        super();
        addSequential(new PrintCommand("[SwitchPostScore Drive To Scale] start!"));

        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL, RampMode.NO_RAMPING);

        driveCommand.addSpeedChange(0, -1);
        driveCommand.addTurn(0, isRight ? -250 : 250);
        driveCommand.addTurn(115, isRight ? -180 : 180); // If wrong sign it will do a 180

        addSequential(driveCommand, DRIVE_RAMP_TIMEOUT);
    }

}