package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SameSideScaleAutonCommand extends CommandGroup {
    public double speed = 0.5;
    public static FieldMapInterface Quad = Robot.getRobotQuadrant();
    
    public SameSideScaleAutonCommand() {
        addParallel(new DrivetrainLineSensorCommand(Quad.getDistanceFromLineSensorToAutoLine()));
        addSequential(new DriveStraightWithRampingCommand(Quad.getTotalDistanceToTravelToReachNullLine()));
        addSequential(new DrivetrainRotateDegreesPIDCommand(Quad.getAngleToReachIdealStartingPointFromNullTerritoryLine()));
        addSequential(new LiftMoveToHeightCommand(84)); //unsure about height
        addSequential(new DriveStraightWithRampingCommand(Quad.getDistanceToTravelToReachScaleCorner()));
        addSequential(new GrabberOpenCommand());
    }
}