package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class DrivetrainLineSensorPlatformZoneCommand extends CommandGroup {
    public static FieldMapInterface Quad = Robot.getRobotQuadrant();

    public DrivetrainLineSensorPlatformZoneCommand() {
        addSequential(new DrivetrainLineSensorCommand(Quad.getDistanceToTravelToReachPlatformZone())); 
        addSequential(new DrivetrainLineSensorCommand(Quad.getDistanceFromEdgeOfPlatformZoneToBump()));
        addSequential(new DrivetrainLineSensorCommand(Quad.getDistanceFromStartingPointToOtherPlatformZoneEdge()));
    }
}