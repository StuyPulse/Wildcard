package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;

import edu.wpi.first.wpilibj.command.PrintCommand;

public class RightSideSwitchAutonCommand extends AutonCommandGroup {
//    private FieldMapInterface quad = Robot.getRobotQuadrant();

    // 114: 2x speed
    private static final double DISTANCE_TOTAL = 110;
    private final static double INITIAL_DRIVE_RAMP_TIMEOUT = 3;

    public RightSideSwitchAutonCommand() {
        super();

        addSequential(new PrintCommand("[RightSideSwitchAuton] start!"));
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL, RampMode.NO_RAMPING);

        driveCommand.addSpeedChange(0, 1); // 100% speed at the start
        driveCommand.addTurn(0, 70);       // Turn to 70 degrees at the start
        driveCommand.addTurn(65, -5);      // Turn to -5 degrees after traveling 65 inches

        // Move the lift up 30 inches after traveling 40 inches
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30), 40));

        // Shoot out with the quisitor after traveling 95 inches
        addParallel(new ConditionalDistanceEncodersCommand(
                new SideSwitchAutonChooserCommand.QuisitorDeacquireTimeCommand(), 95));

        addSequential(driveCommand, INITIAL_DRIVE_RAMP_TIMEOUT);

        addSequential(new PrintCommand("[RightSideSwitchAuton] Post score"));

//        addSequential(new SwitchPostScoreDriveToScaleAutonCommand(true));
        addSequential(new SwitchPostScoreGrabAnotherCubeCommand(true));
    }
}
