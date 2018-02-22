package org.usfirst.frc.team694.robot.commands.auton;


import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DifferentSideScaleAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    private static final double DISTANCE_TOTAL = 410;//450;

    public DifferentSideScaleAutonCommand() {

//        DriveStraightWithRampingCommand rampCommand = new DriveStraightWithRampingCommand(DISTANCE_TOTAL);
        // Uncomment for no ramp down:
        DriveStraightWithRampingCommand rampCommand = new DriveStraightRampUpOnlyCommand(DISTANCE_TOTAL);

        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 76, -140)); // First turn
        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand,  76, 0.4));     // Slow down for turn
        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand,  76 + 100, 1)); // Normal speed after turn
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 76 + 200, -90)); // Realign to prevent overshooting
        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand,  76 + 200, 0.3)); // Slow speed before last turn
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 76 + 260, 15));  // Final turn // 300
        //addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT), DISTANCE_TOTAL - 200));
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT - 18), DISTANCE_TOTAL - 200));
        addSequential(rampCommand);

        addSequential(new GrabberOpenCommand());

    }

}