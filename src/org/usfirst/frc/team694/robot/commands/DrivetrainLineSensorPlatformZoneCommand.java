package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivetrainLineSensorPlatformZoneCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    public DrivetrainLineSensorPlatformZoneCommand() {
        addSequential(new DrivetrainLineSensorCommand(quad.getDistanceToTravelToReachPlatformZone())); 
        addSequential(new DrivetrainLineSensorCommand(quad.getDistanceFromEdgeOfPlatformZoneToBump()));
        addSequential(new DrivetrainLineSensorCommand(quad.getDistanceFromStartingPointToOtherPlatformZoneEdge()));
    }
}