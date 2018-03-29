package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * :( curving was really fun though
 */
public class SimpleSwitchAutonCommand extends CommandGroup {

    // Constants

    // Difference between center line and exchange zone
    private static final double EXTRA_RIGHT_DISTANCE = 12;
    private static final double WALL_TO_SWITCH_DISTANCE = 140;
    private static final double SWITCH_WIDTH = 12*12 + 9 - 20;
    private static final double SWITCH_PLATE_WIDTH = 3*12;

    // Changeable iffyniffs
    private static final double INITIAL_DISTANCE = 20; // MUST BE LESS THAN 98, to not hit pyramid
    private static final double ROTATE_TIME = 1.5;

    public SimpleSwitchAutonCommand(boolean isRight) {
        // Forwards (arbitrary) then turn 90
        addSequential(new DrivetrainMoveInchesEncoderCommand(INITIAL_DISTANCE, 0.5));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? 90 : -90), ROTATE_TIME);

        // Travel to center of switch plate and face switch
        addSequential(new DrivetrainMoveInchesEncoderCommand(
                SWITCH_WIDTH/2 - SWITCH_PLATE_WIDTH/2 + (isRight? EXTRA_RIGHT_DISTANCE : -1*EXTRA_RIGHT_DISTANCE), 1));
        addParallel(new LiftMoveToHeightCommand(30));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0), ROTATE_TIME);

        // "gently move" into that switch and pop that cube
        addSequential(new DrivetrainMoveInchesEncoderCommand(30, 0.5), 2);
        addSequential(new QuisitorDeacquireCommand(), 2);
    }
}
