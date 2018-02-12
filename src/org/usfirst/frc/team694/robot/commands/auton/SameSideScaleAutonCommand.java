package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SameSideScaleAutonCommand extends CommandGroup {
    public double speed = 0.5;
    public static FieldMapInterface Quad = Robot.currentQuad;
    public SameSideScaleAutonCommand() {
        addSequential(new DrivetrainMoveToLineCommand(speed, Quad.getDistanceFromLineSensorToAutoLine()));
        addSequential(new DrivetrainMoveToLineCommand(speed, Quad.getDistanceFromAutoLineToNullTerritory()));
        addSequential(new DrivetrainMoveInchesEncoderCommand(-1 * speed, Quad.getDistanceToMoveBackwardBeforeTurn()));
        //addSequential(new DrivetrainRotateDegreesPIDCommand(Quad.getDegreeOfAngleToReachIdealStartingPointFromNullTerritoryLine()));
        addSequential(new LiftMoveToHeightCommand(84)); //Unsure about this height
        addSequential(new DrivetrainMoveInchesEncoderCommand(speed, Quad.getDistanceToTravelToReachScaleCorner()));
        addSequential(new GrabberOpenCommand());
    }
}