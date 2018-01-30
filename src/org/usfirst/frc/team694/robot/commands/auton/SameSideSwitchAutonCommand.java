package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMap;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SameSideSwitchAutonCommand extends CommandGroup {
    public double speed = 0.5; 
    public SameSideSwitchAutonCommand() {
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE, speed));
        addSequential(new DrivetrainMoveInchesEncoderCommand(FieldMap.DISTANCE_TO_TRAVEL_BEFORE_FIRST_TURN_FROM_AUTO_LINE, speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(90));
        addSequential(new DrivetrainMoveToLineCommand(FieldMap , speed);
        addSequential(new DrivetrainMoveInchesEncoderCommand(FieldMap. , speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(90)); 
        addSequential(new DrivetrainMoveInchesEncoderCommand(FieldMap., speed));
        addSequential(new push);
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
