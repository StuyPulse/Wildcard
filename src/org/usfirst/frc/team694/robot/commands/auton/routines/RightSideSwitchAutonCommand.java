package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;

import edu.wpi.first.wpilibj.command.PrintCommand;

public class RightSideSwitchAutonCommand extends AutonCommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    // 114: 2x speed
    private static final double DISTANCE_TOTAL = 153;
    private final static double INITIAL_DRIVE_RAMP_TIMEOUT = 3;

    public RightSideSwitchAutonCommand() {
        super();
        addSequential(new PrintCommand("[RightSideSwitchAuton] start!"));

        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL, RampMode.NO_RAMPING);

        driveCommand.addSpeedChange(0, 1);
        driveCommand.addTurn(0, 70);
        driveCommand.addTurn(65, -5);

        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30), 40));
        addParallel(new ConditionalDistanceEncodersCommand(
                new SideSwitchAutonChooserCommand.SpatulaDeacquireTimeCommand(), 95));
//        addParallel(new ConditionalDistanceEncodersCommand(new SpatulaDeacquireCommand(), 95));
        addSequential(driveCommand, INITIAL_DRIVE_RAMP_TIMEOUT);

        addSequential(new PrintCommand("[RightSideSwitchAuton] Post score"));
        // Tested, sort of works for right side
//        addSequential(new SwitchPostScoreDriveToScaleAutonCommand(true));
        addSequential(new SwitchPostScoreGrabAnotherCubeCommand(true));
    }

}