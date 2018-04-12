package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class TestCurveCommand extends CommandGroup {

    private static final double DISTANCE_TURN = 10;
    private static final double TURN_ANGLE = 90;

    private static final double TOTAL_DISTANCE = DISTANCE_TURN + 100;

    private static final double DISTANCE_TO_POWER_CUBES = 53;
    public TestCurveCommand() {
        
//        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(TOTAL_DISTANCE, RampMode.NO_RAMPING);
//        driveCommand.addSpeedChange(0, -0.8);
//        driveCommand.addTurn(DISTANCE_TURN, TURN_ANGLE);
        DrivetrainDriveCurveCommand curveToPowerCube = new DrivetrainDriveCurveCommand(DISTANCE_TO_POWER_CUBES);
        curveToPowerCube.addSpeedChange(0.0, -0.6);
        curveToPowerCube.addTurn(42.0, 90.0);
        curveToPowerCube.addTurn(52.0, 0.0);
        addSequential(curveToPowerCube);
    }
}
