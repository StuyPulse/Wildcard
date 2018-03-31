package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;

import edu.wpi.first.wpilibj.command.PrintCommand;

public class AlternativeCurvySwitchAutonCommand extends AutonCommandGroup {

    // Difference between center line and exchange zone
    private static final double EXTRA_RIGHT_DISTANCE = 12;
    private static final double WALL_TO_SWITCH_DISTANCE = 140;
    private static final double SWITCH_WIDTH = 12*12 + 9;
    private static final double SWITCH_PLATE_WIDTH = 3*12;    

    private static final double DISTANCE_LEFT_TOTAL = WALL_TO_SWITCH_DISTANCE + SWITCH_WIDTH/2 - SWITCH_PLATE_WIDTH/2;
    private static final double DISTANCE_RIGHT_TOTAL = DISTANCE_LEFT_TOTAL + EXTRA_RIGHT_DISTANCE;

    // Changeable iffyniffs
    private static final double INITIAL_DISTANCE = 70; // MUST BE LESS THAN 98, to not hit pyramid
    private static final double DRIVE_TIMEOUT = 4;

    public AlternativeCurvySwitchAutonCommand(boolean isRight) {
        super();

        addSequential(new PrintCommand("[AlternativeCurvySwitchAutonCommand] start!"));
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(isRight? DISTANCE_RIGHT_TOTAL : DISTANCE_LEFT_TOTAL, RampMode.NO_RAMPING);

        driveCommand.addSpeedChange(0, 1);
        driveCommand.addTurn(INITIAL_DISTANCE, isRight? 90 : -90);
        driveCommand.addTurn(INITIAL_DISTANCE + SWITCH_WIDTH/2 + (isRight? EXTRA_RIGHT_DISTANCE : 0), 0);

        addSequential(driveCommand, DRIVE_TIMEOUT);
        addSequential(new QuisitorDeacquireCommand(), 2);

        addSequential(new PrintCommand("[AlternativeCurvySwitchAutonCommand] Post score"));

    }
}
