package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMap;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMaxUpCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;


public class DifferentSideScaleAutonCommand extends CommandGroup {
    public double speed = 0.5;
    public DifferentSideScaleAutonCommand() {
        addSequential(new DrivetrainMoveToLineCommand(1, speed));
        addSequential(new DrivetrainMoveInchesEncoderCommand(FieldMap.DISTANCE_TO_TRAVEL_BEFORE_FIRST_TURN_FROM_AUTO_LINE, speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(90));
        addSequential(new DrivetrainMoveToLineCommand(2, speed));
        addParallel(new LiftMaxUpCommand());
        addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
        addSequential(new DrivetrainMoveToLineCommand(1, speed));
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
