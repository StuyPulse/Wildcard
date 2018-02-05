package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMap;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DifferentSideScaleAutonCommand extends CommandGroup {
    public double speed = 0.5;
    public DifferentSideScaleAutonCommand() {
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE, speed));
        addSequential(new DrivetrainMoveInchesEncoderCommand(FieldMap.DISTANCE_TO_TRAVEL_BEFORE_FIRST_TURN_FROM_AUTO_LINE, speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(90));
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE, speed));
        addSequential(new DrivetrainMoveToLineCommand(90, speed));
        addSequential(new DrivetrainMoveToLineCommand(90, speed));
        addSequential(new DrivetrainMoveInchesEncoderCommand(17, speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
        addSequential(new DrivetrainMoveToLineCommand(52.765, speed));
        addSequential(new LiftMoveToHeightCommand(FieldMap.HIGHEST_PLATE_HEIGHT_OF_SCALE + 6));
        addSequential(new GrabberOpenCommand());
        /*
            addSequential(new DrivetrainMoveToLineCommand(1, speed));
            addSequential(new DrivetrainMoveInchesEncoderCommand(FieldMap.DISTANCE_FROM_NULL_TERRITORY_TO_NULL_BUMP, speed));
            addParallel(new LiftMaxUpCommand());
            addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
            addSequential(new DrivetrainMoveInchesEncoderCommand(FieldMap.DISTANCE_FROM_BORDER_TO_SCALE_EDGE/2, speed));
            addSequential(new GrabberOpenCommand());
       */     
    }
}