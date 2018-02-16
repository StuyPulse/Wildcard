package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DifferentSideScaleAutonCommand extends CommandGroup {
    public static FieldMapInterface Quad = Robot.getRobotQuadrant();
    public double speed = 0.5;

    public DifferentSideScaleAutonCommand() {  
        addSequential(new DriveStraightWithRampingCommand(Quad.getTotalDistanceToTravelBeforeTurn()));
        //add line system code using this value: Quad.getDistanceFromLineSensorToAutoLine()
        addSequential(new DrivetrainRotateDegreesPIDCommand(Quad.getAngleToTurnToReachPlatformZone()));
        addSequential(new DriveStraightWithRampingCommand(Quad.getTotalDistanceToTravelToReachOtherSideOfPlatformZone()));

        //add line system code using this value: Quad.getDistanceToTravelToReachPlatformZone()

        //add line system code using this value: Quad.getDistanceFromStartingPointToOtherPlatformZoneEdge()

        addSequential(new DrivetrainRotateDegreesPIDCommand(Quad.getAngleToTurnToReachScaleSide()));

        addSequential(new LiftMoveToHeightCommand(84));//unsure about height

        addSequential(new DriveStraightWithRampingCommand(Quad.getTotalDistanceToTravelToReachScaleSide()));

        //add line system code using this value: Quad.getDistanceFromRobotAfterTwoTurnsToNullTerritory()

        addSequential(new GrabberOpenCommand());
    }
}