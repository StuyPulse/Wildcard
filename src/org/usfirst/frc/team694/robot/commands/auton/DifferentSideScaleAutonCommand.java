package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMap;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMaxUpCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class DifferentSideScaleAutonCommand extends CommandGroup {
    public double speed = 0.5;
    public double placeholder = -1;
    public DifferentSideScaleAutonCommand() {
        /*addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE, speed));
        addSequential(new DrivetrainMoveInchesEncoderCommand(FieldMap.DISTANCE_TO_TRAVEL_BEFORE_FIRST_TURN_FROM_AUTO_LINE, speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(90));
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_HALVED, speed));
        addSequential(new DrivetrainMoveToLineCommand(90, speed));
        addSequential(new DrivetrainMoveToLineCommand(90, speed));
        addSequential(new DrivetrainMoveInchesEncoderCommand(17, speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
        addSequential(new DrivetrainMoveToLineCommand(52.765, speed));
        addSequential(new LiftMaxUpCommand());
        addSequential(new GrabberOpenCommand());*/
        /*
            addSequential(new DrivetrainMoveToLineCommand(1, speed));
            addSequential(new DrivetrainMoveInchesEncoderCommand(FieldMap.DISTANCE_FROM_NULL_TERRITORY_TO_NULL_BUMP, speed));
            addParallel(new LiftMaxUpCommand());
            addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
            addSequential(new DrivetrainMoveInchesEncoderCommand(FieldMap.DISTANCE_FROM_BORDER_TO_SCALE_EDGE/2, speed));
            addSequential(new GrabberOpenCommand());
       */     
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE, speed));
        addSequential(new DrivetrainMoveInchesEncoderCommand(FieldMap.DISTANCE_TO_TRAVEL_BEFORE_FIRST_TURN_FROM_AUTO_LINE, speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(90));
        addSequential(new DrivetrainMoveToLineCommand(DISTANCE_TO_TRAVEL_TO_REACH_PLATFORM_ZONE_EDGE, speed));
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_ANOTHER, speed));
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_TO_DRIVE_OUT_AFTER_PLATFORM_ZONE, speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_TO_TRAVEL_TO_REACH_SCALE_SIDE, speed));
        addSequential(new LiftMaxUpCommand());
        addSequential(new GrabberOpenCommand());
    }
}
