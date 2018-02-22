package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.DrivetrainHighGearCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainLowGearCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSideSwitchAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    // 114: 2x speed
    private static final double DISTANCE_TOTAL = 118;

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

//        addSequential(new DrivetrainLowGearCommand());

        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand, 0, 3)); // Move at 0.3 speed
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 0, -80)); // Start turning
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 65, 5)); // Turn back, ish
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(15), 40));
        // ultra fast and ultra fun but totally illegal
//        addParallel(new ConditionalDistanceEncodersCommand(new SpatulaDeacquireCommand(), 80));
        addSequential(rampCommand);


        // Fun but illegal deacquire
        addSequential(new SpatulaDeacquireCommand(), 1);

//        addSequential(new DrivetrainHighGearCommand());

    }
}
