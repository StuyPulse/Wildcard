package org.usfirst.frc.team694.robot.commands.auton;


import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DifferentSideScaleAutonCommand extends CommandGroup {
    public static FieldMapInterface Quad = Robot.getRobotQuadrant();
    public double speed = 0.5;

    public DifferentSideScaleAutonCommand() {  
        addParallel(new DrivetrainLineSensorCommand(Quad.getDistanceFromLineSensorToAutoLine()));
        addSequential(new DriveStraightWithRampingCommand(Quad.getTotalDistanceToTravelBeforeTurn()));
        
        addSequential(new DrivetrainRotateDegreesPIDCommand(Quad.getAngleToTurnToReachPlatformZone()));

        addParallel(new DrivetrainLineSensorPlatformZoneCommand());
        addSequential(new DriveStraightWithRampingCommand(Quad.getTotalDistanceToTravelToReachOtherSideOfPlatformZone()));
        
        addSequential(new DrivetrainRotateDegreesPIDCommand(Quad.getAngleToTurnToReachScaleSide()));

        addSequential(new LiftMoveToHeightCommand(84));//unsure about height

        addParallel(new DrivetrainLineSensorCommand(Quad.getDistanceFromRobotAfterTwoTurnsToNullTerritory()));
        addSequential(new DriveStraightWithRampingCommand(Quad.getTotalDistanceToTravelToReachScaleSide()));

        addSequential(new GrabberOpenCommand());

    }

}