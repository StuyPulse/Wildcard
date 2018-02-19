package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SameSideSwitchAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();
    public SameSideSwitchAutonCommand() {
        //addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromLineSensorToAutoLine()));
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToDriveForwardToReachSwitch()));
        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));       
        addSequential(new DriveStraightWithRampingCommand(quad.getDistanceToDriveForwardIntoSwitchEdge()));
        addSequential(new GrabberOpenCommand());
        addSequential(new SpatulaDeacquireCommand());
    }
}