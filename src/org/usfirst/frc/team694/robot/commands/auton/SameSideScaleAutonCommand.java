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
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToTravelToReachNullLine()), 4.25);

        addParallel(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToReachIdealStartingPointFromNullTerritoryLine() - 30));
        addSequential(new LiftMoveToHeightCommand(89 - RobotMap.LIFT_MIN_HEIGHT));
        addSequential(new DriveStraightWithRampingCommand(quad.getDistanceToTravelToReachScaleCorner() + 10), 3);
        addSequential(new GrabberOpenCommand());
    }
}