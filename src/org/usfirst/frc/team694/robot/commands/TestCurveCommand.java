package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class TestCurveCommand extends CommandGroup {

    private static final double DISTANCE_TURN = 10;
    private static final double TURN_ANGLE = -90;

    private static final double TOTAL_DISTANCE = DISTANCE_TURN + 100;
    public TestCurveCommand() {
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(TOTAL_DISTANCE);
        driveCommand.addSpeedChange(0, 0.8);
        driveCommand.addTurn(DISTANCE_TURN, TURN_ANGLE);

        addSequential(driveCommand);
    }
}
