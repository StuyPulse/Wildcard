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
        addSequential(new DriveStraightWithRampingCommand(226)); //TODO: Please insert another magic number
        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));
        addSequential(new DriveStraightWithRampingCommand(202.31)); //TODO: Please insert another magic number
        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));
        addSequential(new DriveStraightWithRampingCommand(37.5)); //TODO: Please insert another magic number
        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));
        addSequential(new DriveStraightWithRampingCommand(20)); //TODO: Please insert magic number
        addSequential(new GrabberOpenCommand());
        addSequential(new SpatulaDeacquireCommand());
    }
}
