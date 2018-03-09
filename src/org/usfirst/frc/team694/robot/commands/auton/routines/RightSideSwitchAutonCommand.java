package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.CrabArmStopCommand;
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
        // Uncomment for no ramp down:
//        DriveStraightWithRampingCommand rampCommand = new DriveStraightRampUpOnlyCommand(DISTANCE_TOTAL);
        addSequential(new PrintCommand("[RightSideSwitchAuton] start!"));
        
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL, RampMode.NO_RAMP_DOWN);
        
        driveCommand.addSpeedChange(0, 1);
        driveCommand.addTurn(0, 70);
        driveCommand.addTurn(65, -5);

//        DriveStraightWithRampingCommand rampCommand = new DriveStraightNoRampingLimitCommand(DISTANCE_TOTAL);

//        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand, 0, 1), INITIAL_DRIVE_RAMP_TIMEOUT);
//        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 0, 70), INITIAL_DRIVE_RAMP_TIMEOUT); // Start turning
//        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 65, -5), INITIAL_DRIVE_RAMP_TIMEOUT); // Turn back, ish
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30), 40));
        addParallel(new ConditionalDistanceEncodersCommand(
                new SideSwitchAutonChooserCommand.SpatulaDeacquireTimeCommand(), 95));

//        addSequential(rampCommand, INITIAL_DRIVE_RAMP_TIMEOUT);

        addSequential(new CrabArmStopCommand());

        addSequential(new PrintCommand("[RightSideSwitchAuton] Post score"));
        addSequential(new SwitchPostScoreExchangeScoreCommand(true));
    }
    
}