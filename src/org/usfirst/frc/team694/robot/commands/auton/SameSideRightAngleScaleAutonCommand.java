package org.usfirst.frc.team694.robot.commands.auton;


import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SameSideRightAngleScaleAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    public SameSideRightAngleScaleAutonCommand() {
//        addParallel(new DrivetrainLineSensorCommand(FILLME));
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceFromFrontOfBotToNullBump()));
      
        addSequential(new DrivetrainRotateRelativeDegreesPIDCommand(quad.getDistanceFromRobotAfterTwoTurnsToNullTerritory()));
        
        addSequential(new DriveStraightWithRampingCommand(quad.getDistanceToMoveBackward()));
        
        addSequential(new LiftMoveToHeightCommand(84)); //Unsure about height
        
        addSequential(new DriveStraightWithRampingCommand(quad.getDistanceToReachScaleEdge())); 
        
        addSequential(new GrabberOpenCommand());

    }
}
