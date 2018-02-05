package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMap;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SameSideScaleAutonCommand extends CommandGroup {
    public double speed = 0.5;
    public SameSideScaleAutonCommand() {
        addSequential(new DrivetrainMoveToLineCommand(speed, FieldMap.DISTANCE_FROM_LINE_SENSOR_TO_AUTO_LINE));
        addSequential(new DrivetrainMoveToLineCommand(speed, FieldMap.DISTANCE_FROM_AUTO_LINE_TO_NULL_TERRITORY));
        addSequential(new DrivetrainMoveInchesEncoderCommand(-1 * speed, FieldMap.DISTANCE_TO_MOVE_BACKWARD_BEFORE_TURN));
        addSequential(new DrivetrainRotateDegreesPIDCommand(FieldMap.DEGREE_OF_ANGLE_TO_REACH_IDEAL_STARTING_POINT_FROM_NULL_TERRITORY_LINE));
        addSequential(new LiftMoveToHeightCommand(FieldMap.HIGHEST_PLATE_HEIGHT_OF_SCALE + 6));
        addSequential(new DrivetrainMoveInchesEncoderCommand(speed, FieldMap.DISTANCE_TO_TRAVEL_TO_REACH_SCALE_CORNER));
        addSequential(new GrabberOpenCommand());
    }
}