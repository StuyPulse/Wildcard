
package org.usfirst.frc.team694.robot.commands.auton.routines;


import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateRelativeDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class SimpleDifferentSideScaleAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

//    private static final double DISTANCE_TOTAL = 410;//450;

    public SimpleDifferentSideScaleAutonCommand() {
        boolean isRight = Robot.isRobotStartingOnRight();

        addSequential(new PrintCommand("[DifferentSideScale] Simple Different Side! RIGHT? " + isRight));
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToTravelBeforeTurn() + 3 + 3));

        addSequential(new DrivetrainRotateRelativeDegreesPIDCommand((isRight? -1 : 1) * quad.getAngleToTurnToReachPlatformZone()));

        //addParallel(new DrivetrainLineSensorPlatformZoneCommand());
        addSequential(new DriveStraightWithRampingCommand(203/*quad.getTotalDistanceToTravelToReachOtherSideOfPlatformZone()*/));

        addSequential(new DrivetrainRotateRelativeDegreesPIDCommand((isRight? -1 : 1) * quad.getAngleToTurnToReachScaleSide()), 2);
        // Height used to be 89 - minheight
        addSequential(new LiftMoveToHeightCommand(83 - RobotMap.MIN_HEIGHT_OF_LIFT));//unsure about height

        //addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromRobotAfterTwoTurnsToNullTerritory()));
//        addSequential(new DriveStraightWithRampingCommand(53 + 12));//quad.getTotalDistanceToTravelToReachScaleSide() - 5));
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.6,43), 2.5);//quad.getTotalDistanceToTravelToReachScaleSide() - 5));

        addSequential(new QuisitorOpenCommand());
        // Add me in
        addSequential(new ScaleGrabCubeAfterScoringCommand(!isRight));
        addSequential(new ScaleScoreSecondTimeCommand(!isRight));

    }

}