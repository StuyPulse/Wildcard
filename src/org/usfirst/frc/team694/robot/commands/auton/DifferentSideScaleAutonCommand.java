package org.usfirst.frc.team694.robot.commands.auton;


import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DifferentSideScaleAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    public DifferentSideScaleAutonCommand() {  
        //addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromLineSensorToAutoLine()));
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToTravelBeforeTurn()));

        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachPlatformZone()));

        //addParallel(new DrivetrainLineSensorPlatformZoneCommand());
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToTravelToReachOtherSideOfPlatformZone()));

        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleSide()));

        addSequential(new LiftMoveToHeightCommand(84));//unsure about height

        //addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromRobotAfterTwoTurnsToNullTerritory()));
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToTravelToReachScaleSide()));

        addSequential(new GrabberOpenCommand());

    }

}