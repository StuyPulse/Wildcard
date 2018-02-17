package org.usfirst.frc.team694.robot.commands.auton;


import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SameSideRightAngleAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    public SameSideRightAngleAutonCommand() {
        addParallel(new DrivetrainLineSensorReachNullCommand());
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceFromFrontOfBotToNullBump()));
      
        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getDistanceFromRobotAfterTwoTurnsToNullTerritory()));
        
        addSequential(new DriveStraightWithRampingCommand(quad.getDistanceToMoveBackward()));
        
        addSequential(new LiftMoveToHeightCommand(84)); //Unsure about height
        
        addSequential(new DriveStraightWithRampingCommand(quad.getDistanceToReachScaleEdge())); 
        
        addSequential(new GrabberOpenCommand());
    }
}
