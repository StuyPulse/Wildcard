package org.usfirst.frc.team694.robot.commands.auton;


import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainLineSensorReachNullCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SameSideRightAngleAutonCommand extends CommandGroup {
    public static FieldMapInterface Quad = Robot.getRobotQuadrant();
    public double speed = 0.5;

    public SameSideRightAngleAutonCommand() {
        addParallel(new DrivetrainLineSensorReachNullCommand());
        addSequential(new DriveStraightWithRampingCommand(324));//TODO: Make magic number for distance from starting point to null bump
      
        addSequential(new DrivetrainRotateDegreesPIDCommand(Quad.getDistanceFromRobotAfterTwoTurnsToNullTerritory()));
        
        addSequential(new DriveStraightWithRampingCommand(46.94)); //TODO: Make magic number for distance to travel backward to reach wall
        
        addSequential(new LiftMoveToHeightCommand(84)); //Unsure about height
        
        addSequential(new DriveStraightWithRampingCommand(Quad.getDistanceFromBorderToScaleEdge())); //TODO: Make ANOTHER magic number because life is hard </3

        addSequential(new GrabberOpenCommand());
    }
}
