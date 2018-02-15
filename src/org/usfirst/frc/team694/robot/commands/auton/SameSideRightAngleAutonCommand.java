package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SameSideRightAngleAutonCommand extends CommandGroup {
    public double speed = 0.5;
    public SameSideRightAngleAutonCommand() {

        addSequential(new DrivetrainMoveToLineCommand(speed, FieldMapInterface.DISTANCE_FROM_LINE_SENSOR_TO_AUTO_LINE));
        addSequential(new DrivetrainMoveToLineCommand(speed, FieldMap.DISTANCE_FROM_NULL_BUMP_TO_AUTO_LINE));
        addSequential(new DrivetrainMoveInchesEncoderCommand(speed, FieldMapInterface.DISTANCE_TO_MOVE_INTO_NULL_BUMP));
        addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
        addSequential(new DrivetrainMoveInchesEncoderCommand(-1 * speed, FieldMap.DISTANCE_TO_TRAVEL_BACKWARDS));
        addSequential(new LiftMoveToHeightCommand(84)); //unsure about this height
        addSequential(new DrivetrainMoveInchesEncoderCommand(speed, FieldMap.DISTANCE_TO_TRAVEL_TO_REACH_SCALE_EDGE));
        addSequential(new GrabberOpenCommand());
    }
}
