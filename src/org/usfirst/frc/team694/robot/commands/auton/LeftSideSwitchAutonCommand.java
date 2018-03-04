package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class LeftSideSwitchAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    // 114: 2x speed
    private static final double DISTANCE_TOTAL = 153;

    public LeftSideSwitchAutonCommand() {
        //addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromLineSensorToAutoLine()));
//        addSequential(new DriveStraightWithRampingCommand(226)); //TODO: Please insert another magic number
//        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));
//        addSequential(new DriveStraightWithRampingCommand(202.31)); //TODO: Please insert another magic number
//        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));
//        addSequential(new DriveStraightWithRampingCommand(37.5)); //TODO: Please insert another magic number
//        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));
//        addSequential(new DriveStraightWithRampingCommand(20)); //TODO: Please insert magic number
//        addSequential(new GrabberOpenCommand());
//        addSequential(new SpatulaDeacquireCommand());
//        DriveStraightWithRampingCommand rampCommand = new DriveStraightRampUpOnlyCommand(DISTANCE_TOTAL);

        DriveStraightWithRampingCommand rampCommand = new DriveStraightNoRampingLimitCommand(DISTANCE_TOTAL);

        addSequential(new PrintCommand("[LeftSideSwitchAuton] start!"));

//        addSequential(new DrivetrainLowGearCommand());

        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand, 0, 1)); // Move at 0.3 speed
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 0, -80)); // Start turning
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 65, 5)); // Turn back, ish
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30), 40));
        // ultra fast and ultra fun but totally illegal
//        addParallel(new ConditionalDistanceEncodersCommand(new SpatulaDeacquireCommand(), 95));
        addParallel(new ConditionalDistanceEncodersCommand(
                new SideSwitchAutonChooserCommand.SpatulaDeacquireTimeCommand(), 95));
        addSequential(rampCommand, 3.5);

        addSequential(new SwitchPostScoreExchangeScoreCommand(false));

    }
}
