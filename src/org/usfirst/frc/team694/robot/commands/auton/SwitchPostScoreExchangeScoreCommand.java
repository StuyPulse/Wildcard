package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.FlapAndFlipDownCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class SwitchPostScoreExchangeScoreCommand extends CommandGroup {

    private static final double SWITCH_WIDTH = 189.5;
    // The extra distance that the center of the exchange zone is from the center of the field on the right side
    // 6 is an unknown buffer: Distance from left side of exchange colorbox to the actual exchange hole
    private static final double EXTRA_DISTANCE_FOR_RIGHT_SIDE = 12 + 6 + 21.0/2.0; // 21 = width of exchange zone

    // How far our bot's center ends up from the scale's edge (use the edge closest to the edge of the scale)
    private static final double DISTANCE_BOT_CENTER_IS_FROM_SCALE_EDGE = 12 + RobotMap.WIDTH_OF_BOT / 2;

    public SwitchPostScoreExchangeScoreCommand(boolean isOnRight) {
        addSequential(new DrivetrainMoveInchesEncoderCommand(42.0 - RobotMap.LENGTH_OF_BOT/2 - 11.0/2.0, -0.3));

        addParallel(new FlapAndFlipDownCommand());
        if (isOnRight) {
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-90));
            // Grab cube and continue moving till we are ready to turn to the exchange
            addSequential(new DriveStraightWithRampingCommand(SWITCH_WIDTH/2.0 + EXTRA_DISTANCE_FOR_RIGHT_SIDE - DISTANCE_BOT_CENTER_IS_FROM_SCALE_EDGE));
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-180));
        } else {
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(90));
            // Move to grab the cube, then move back to align to the exchange
            addSequential(new DriveStraightWithRampingCommand(SWITCH_WIDTH/2.0 - DISTANCE_BOT_CENTER_IS_FROM_SCALE_EDGE));
            addSequential(new DrivetrainMoveInchesEncoderCommand(-0.3, EXTRA_DISTANCE_FOR_RIGHT_SIDE));
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(180));
        }

        // Slam into that exchange
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.8, 130), 2.5);

    }
}
