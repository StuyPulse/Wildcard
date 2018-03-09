package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;

import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class LeftSideSwitchAutonCommand extends AutonCommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    // 114: 2x speed
    private static final double DISTANCE_TOTAL = 153;
    private final static double INITIAL_DRIVE_RAMP_TIMEOUT = 3.5;

    public LeftSideSwitchAutonCommand() {
        super();

        addSequential(new PrintCommand("[LeftSideSwitchAuton] start!"));

        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL);
        driveCommand.addSpeedChange(0, 1);
        driveCommand.addTurn(0, -80);
        driveCommand.addTurn(65, 5);

//        DriveStraightWithRampingCommand rampCommand = new DriveStraightNoRampingLimitCommand(DISTANCE_TOTAL);
//
//        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand, 0, 1)); // Move at 0.3 speed
//        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 0, -80)); // Start turning
//        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 65, 5)); // Turn back, ish
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30), 40));
        addParallel(new ConditionalDistanceEncodersCommand(
                new SideSwitchAutonChooserCommand.SpatulaDeacquireTimeCommand(), 95));
//        addSequential(rampCommand, INITIAL_DRIVE_RAMP_TIMEOUT);
        addSequential(driveCommand, INITIAL_DRIVE_RAMP_TIMEOUT);

        addSequential(new PrintCommand("[LeftSideSwitchAuton] Post score"));

        addSequential(new SwitchPostScoreExchangeScoreCommand(false));

    }

}
