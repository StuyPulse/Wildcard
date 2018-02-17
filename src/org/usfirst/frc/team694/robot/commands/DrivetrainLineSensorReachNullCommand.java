package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class DrivetrainLineSensorReachNullCommand extends CommandGroup {
    public static FieldMapInterface Quad = Robot.getRobotQuadrant();
    public DrivetrainLineSensorReachNullCommand() {
        addSequential(new DrivetrainLineSensorCommand(Quad.getDistanceFromLineSensorToAutoLine()));
        addSequential(new DrivetrainLineSensorCommand(Quad.getTotalDistanceToTravelToReachNullLine()));
    }
}
