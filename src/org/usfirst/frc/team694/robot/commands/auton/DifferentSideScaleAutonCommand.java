package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMap;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMaxUpCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class DifferentSideScaleAutonCommand extends CommandGroup {
    public double speed = 0.5;
    public DifferentSideScaleAutonCommand() {  
        addSequential(new DrivetrainMoveToLineCommand(speed, FieldMap.DISTANCE_FROM_LINE_SENSOR_TO_AUTO_LINE));
        addSequential(new DrivetrainMoveInchesEncoderCommand(speed, FieldMap.DISTANCE_TO_TRAVEL_BEFORE_FIRST_TURN_FROM_AUTO_LINE));
        addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
        addSequential(new DrivetrainMoveToLineCommand(speed, FieldMap.DISTANCE_TO_TRAVEL_TO_REACH_PLATFORM_ZONE_EDGE));
        addSequential(new DrivetrainMoveToLineCommand(speed, FieldMap.DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_ANOTHER));
        addSequential(new DrivetrainMoveToLineCommand(speed, FieldMap.DISTANCE_TO_DRIVE_OUT_AFTER_PLATFORM_ZONE));
        addSequential(new DrivetrainRotateDegreesPIDCommand(90));
        addSequential(new LiftMaxUpCommand());
        addSequential(new DrivetrainMoveToLineCommand(speed, FieldMap.DISTANCE_TO_TRAVEL_TO_REACH_SCALE_SIDE));
        addSequential(new GrabberOpenCommand());
    }
}
