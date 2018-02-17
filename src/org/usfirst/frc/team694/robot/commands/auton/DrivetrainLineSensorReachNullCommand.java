package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivetrainLineSensorReachNullCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();
    
    public DrivetrainLineSensorReachNullCommand() {
        addSequential(new DrivetrainLineSensorCommand(quad.getDistanceFromLineSensorToAutoLine()));
        addSequential(new DrivetrainLineSensorCommand(quad.getTotalDistanceToTravelToReachNullLine()));
    }
}
