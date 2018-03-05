package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.CrabArrowAcquireUntilAcquiredCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchPostScoreDriveIntoCenterFieldCommand extends CommandGroup {

    private static final double SWITCH_WIDTH = 189.5;

    // How far our bot's center ends up from the scale's edge (use the edge closest to the edge of the scale)
    private static final double DISTANCE_BOT_CENTER_IS_FROM_SCALE_EDGE = 12 + RobotMap.WIDTH_OF_BOT / 2;

    public SwitchPostScoreDriveIntoCenterFieldCommand(boolean isOnRight) {
        // Drive back to proper cube pyramid distance
        addSequential(new DrivetrainMoveInchesEncoderCommand(42.0 - RobotMap.LENGTH_OF_BOT/2 - 11.0/2.0, -0.3));

        // Face the cubes to grab
        if (isOnRight) {
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-90));
        } else {
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(90));
        }
        // Grab cube and then go back
        addParallel(new CrabArrowAcquireUntilAcquiredCommand(), 5);
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.4, SWITCH_WIDTH/2.0 - DISTANCE_BOT_CENTER_IS_FROM_SCALE_EDGE));
        addSequential(new DrivetrainMoveInchesEncoderCommand(-0.4, SWITCH_WIDTH/2.0 - DISTANCE_BOT_CENTER_IS_FROM_SCALE_EDGE));

        // Face the switch
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));

        // Slam into that switch and score again
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.8, 20), 1.5);
        addSequential(new SpatulaDeacquireCommand(), 1.5);

    }
}
