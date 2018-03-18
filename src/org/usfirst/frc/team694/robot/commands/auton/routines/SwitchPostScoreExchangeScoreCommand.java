package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

/**
 * This auton assumes that we have just grabbed a cube from the exchange
 * without moving back, facing forward on the field 
 * (with the bot's back bumper parallel to the starting line)
 */

public class SwitchPostScoreExchangeScoreCommand extends AutonCommandGroup {

    private static final double SWITCH_WIDTH = 189.5;
    // The extra distance that the center of the exchange zone is from the center of the field on the right side
    // 6 is an unknown buffer: Distance from left side of exchange colorbox to the actual exchange hole
    private static final double EXTRA_DISTANCE_FOR_RIGHT_SIDE = 12 + 6 + 21.0/2.0; // 21 = width of exchange zone

    // How far our bot's center ends up from the scale's edge (use the edge closest to the edge of the scale)
    private static final double DISTANCE_BOT_CENTER_IS_FROM_SCALE_EDGE = 12 + RobotMap.WIDTH_OF_BOT / 2;

    public SwitchPostScoreExchangeScoreCommand(boolean isRight) {

        // Back up
        addSequential(new DrivetrainMoveInchesEncoderCommand(-20, -1));

        // Exchange will always be behind us, to the left
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-90));

        // Align with exchange
        addSequential(new DrivetrainMoveInchesEncoderCommand(EXTRA_DISTANCE_FOR_RIGHT_SIDE, 0.2));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-180));

        // Charge forth
        addSequential(new DrivetrainMoveInchesEncoderCommand(100, 0.5), 3);

        // Score!
        addSequential(new SpatulaDeacquireCommand(), 0.8);

        // Old method grabs cubes sideways
//        //Move back so we can not slam into the cube pyramid
//        Old, untested system that grabs the cube on its side
//        addSequential(new DrivetrainMoveInchesEncoderCommand(42.0 - RobotMap.LENGTH_OF_BOT/2 - 11.0/2.0, -0.3));
//
//        addParallel(new CrabArrowAcquireCommand());
//        if (isOnRight) {
//            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-90));
//            // Grab cube and continue moving till we are ready to turn to the exchange
//            addSequential(new DriveStraightWithRampingCommand(SWITCH_WIDTH/2.0 + EXTRA_DISTANCE_FOR_RIGHT_SIDE - DISTANCE_BOT_CENTER_IS_FROM_SCALE_EDGE));
//            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-180));
//        } else {
//            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(90));
//            // Move to grab the cube, then move back to align to the exchange
//            addSequential(new DriveStraightWithRampingCommand(SWITCH_WIDTH/2.0 - DISTANCE_BOT_CENTER_IS_FROM_SCALE_EDGE));
//            addSequential(new DrivetrainMoveInchesEncoderCommand(-0.3, EXTRA_DISTANCE_FOR_RIGHT_SIDE));
//            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(180));
//        }
//
//        // Slam into that exchange
//        addSequential(new DrivetrainMoveInchesEncoderCommand(0.8, 130), 2.5);

    }
}
