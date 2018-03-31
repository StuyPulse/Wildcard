package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;

import edu.wpi.first.wpilibj.command.PrintCommand;

public class LeftSideSwitchAutonCommand extends AutonCommandGroup {
//    private FieldMapInterface quad = Robot.getRobotQuadrant();

    // 114: 2x speed
    private static final double DISTANCE_TOTAL = 95; // 125
    private final static double INITIAL_DRIVE_RAMP_TIMEOUT = 3.5;

    public LeftSideSwitchAutonCommand() {
        super();

        addSequential(new PrintCommand("[LeftSideSwitchAuton] start!"));
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL, RampMode.NO_RAMPING);

        driveCommand.addSpeedChange(0, 1);
        driveCommand.addTurn(0, -80);
        //driveCommand.addSpeedChange(65, 0.5);
        driveCommand.addTurn(65, 5);
        //driveCommand.addSpeedChange(95, 0);

        // Move the lift up 30 inches after traveling 40 inches
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30), 40));

        // Shoot out with the quisitor after traveling 95 inches
        // Too far!
//        addParallel(new ConditionalDistanceEncodersCommand(
//                new SideSwitchAutonChooserCommand.QuisitorDeacquireTimeCommand(), 95));

//        addParallel(new ConditionalDistanceEncodersCommand(new QuisitorOpenCommand(), 95));

        addSequential(driveCommand, INITIAL_DRIVE_RAMP_TIMEOUT);
//        addSequential(new WaitCommand(1));
        addSequential(new QuisitorDeacquireCommand(), 2);

        addSequential(new PrintCommand("[LeftSideSwitchAuton] Post score"));

//      addSequential(new SwitchPostScoreGrabAnotherCubeCommand(false));
//        addSequential(new SwitchPostScoreExchangeScoreCommand(false));
    }
}