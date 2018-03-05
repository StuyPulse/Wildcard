package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.CrabArmStopCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class RightSideSwitchAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    // 114: 2x speed
    private static final double DISTANCE_TOTAL = 153;
    private final static double INITIAL_DRIVE_RAMP_TIMEOUT = 3;

    public RightSideSwitchAutonCommand() {
        //addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromLineSensorToAutoLine()));
//        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToDriveForwardToReachSwitch()));
//        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));       
//        addSequential(new DriveStraightWithRampingCommand(quad.getDistanceToDriveForwardIntoSwitchEdge()));

//        DriveStraightWithRampingCommand rampCommand = new DriveStraightWithRampingCommand(DISTANCE_TOTAL);
        // Uncomment for no ramp down:
//        DriveStraightWithRampingCommand rampCommand = new DriveStraightRampUpOnlyCommand(DISTANCE_TOTAL);
        addSequential(new PrintCommand("[RightSideSwitchAuton] start!"));
        DriveStraightWithRampingCommand rampCommand = new DriveStraightNoRampingLimitCommand(DISTANCE_TOTAL);

        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand, 0, 1), INITIAL_DRIVE_RAMP_TIMEOUT);
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 0, 70), INITIAL_DRIVE_RAMP_TIMEOUT); // Start turning
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 65, -5), INITIAL_DRIVE_RAMP_TIMEOUT); // Turn back, ish
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30), 40), INITIAL_DRIVE_RAMP_TIMEOUT);
        // ultra fast and ultra fun but totally illegal
//        addParallel(new ConditionalDistanceEncodersCommand(new SpatulaDeacquireCommand(), 95));
        addParallel(new ConditionalDistanceEncodersCommand(
                new SideSwitchAutonChooserCommand.SpatulaDeacquireTimeCommand(), 95), INITIAL_DRIVE_RAMP_TIMEOUT);

        addSequential(rampCommand, INITIAL_DRIVE_RAMP_TIMEOUT);

        addSequential(new CrabArmStopCommand());

        addSequential(new PrintCommand("[RightSideSwitchAuton] Post score"));
        addSequential(new SwitchPostScoreExchangeScoreCommand(true));
    }
    
}