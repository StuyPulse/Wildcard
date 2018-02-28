package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DifferentSideSwitchAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    public DifferentSideSwitchAutonCommand() {
        //addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromLineSensorToAutoLine()));
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToTravelBeforeTurn())); 
        addSequential(new DrivetrainRotateRelativeDegreesPIDCommand(quad.getAngleToTurnToReachPlatformZone()));
        addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToTravelAcrossPlatformZoneToReachSwitch()));
        addSequential(new DrivetrainRotateRelativeDegreesPIDCommand(quad.getAngleToReachSwitch()));
        addSequential(new DriveStraightWithRampingCommand(quad.getDistanceToTravelToReachSwitch()));
        addSequential(new DrivetrainRotateRelativeDegreesPIDCommand(quad.getAngleToReachSwitchEdge()));
        addSequential(new DriveStraightWithRampingCommand(quad.getDistanceToDriveForwardIntoSwitchEdge()));
        addSequential(new GrabberOpenCommand());
        addSequential(new SpatulaDeacquireCommand());
    }
}
