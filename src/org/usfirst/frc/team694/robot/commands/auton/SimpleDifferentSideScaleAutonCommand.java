package org.usfirst.frc.team694.robot.commands.auton;


import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SimpleDifferentSideScaleAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

//    private static final double DISTANCE_TOTAL = 410;//450;

    public SimpleDifferentSideScaleAutonCommand() {

        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToTravelBeforeTurn() + 3 + 3));


        addSequential(new DrivetrainRotateDegreesPIDCommand(-1 * quad.getAngleToTurnToReachPlatformZone()));

        //addParallel(new DrivetrainLineSensorPlatformZoneCommand());
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToTravelToReachOtherSideOfPlatformZone() + 15 + 6));

        addParallel(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT));//unsure about height
        addSequential(new DrivetrainRotateDegreesPIDCommand(-1 * quad.getAngleToTurnToReachScaleSide()), 2);

        //addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromRobotAfterTwoTurnsToNullTerritory()));
//        addSequential(new DriveStraightWithRampingCommand(53 + 12));//quad.getTotalDistanceToTravelToReachScaleSide() - 5));
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.6,43), 2.5);//quad.getTotalDistanceToTravelToReachScaleSide() - 5));

        addSequential(new GrabberOpenCommand());
        
        addSequential(new DrivetrainMoveInchesEncoderCommand(-0.1, 40));
    }

}