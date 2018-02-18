package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SameSideScaleAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    public SameSideScaleAutonCommand() {
        //addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromLineSensorToAutoLine()));
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToTravelToReachNullLine() - RobotMap.LENGTH_OF_BOT));
        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToReachIdealStartingPointFromNullTerritoryLine()));
        //addSequential(new LiftMoveToHeightCommand(84)); //unsure about height
        addSequential(new DriveStraightWithRampingCommand(quad.getDistanceToTravelToReachScaleCorner()));
        addSequential(new GrabberOpenCommand());
    }
}