package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SameSideScaleAutonCommand extends CommandGroup {
    public double speed = 0.5;
    public static FieldMapInterface Quad = Robot.getRobotQuadrant();
    public SameSideScaleAutonCommand() {
        addSequential(new DrivetrainMoveInchesEncoderCommand(speed, Quad.getTotalDistanceToTravelToReachNullLine()));
        //add line system code using this value: Quad.getDistanceFromLineSensorToAutoLine()
        addSequential(new DrivetrainRotateDegreesPIDCommand(Quad.getDegreeOfAngleToReachIdealStartingPointFromNullTerritoryLine()));
        addSequential(new LiftMoveToHeightCommand(84)); //unsure about height
        addSequential(new DrivetrainMoveInchesEncoderCommand(speed, Quad.getDistanceToTravelToReachScaleCorner()));
        addSequential(new GrabberOpenCommand());
    }
}