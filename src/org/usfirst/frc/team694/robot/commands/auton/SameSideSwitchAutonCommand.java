package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainLineSensorCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SameSideSwitchAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();
    
    public SameSideSwitchAutonCommand() {
        addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromLineSensorToAutoLine()));
        addSequential(new DriveStraightWithRampingCommand(164)); //TODO: Make magic number in FieldMap (srry Pak)
        
        addSequential(new DrivetrainRotateDegreesPIDCommand(-90)); //TODO: Make another magic number in FieldMap
        
        addSequential(new DriveStraightWithRampingCommand(-46.94)); //TODO: Make yet another magic number in FieldMap
        
        
    }
}
