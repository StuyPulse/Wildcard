package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.WaitUntilCubeDetectedCommand;
import org.usfirst.frc.team694.robot.commands.auton.WaitUntilLiftGoesBelowHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Grabs cubes from the center of the platform side switch
 */
public class KillerTreesDoubleCubeSwitchAutonCommand extends CommandGroup {

    public KillerTreesDoubleCubeSwitchAutonCommand(boolean isRobotRight) {

        // Go first distance and turn
        addSequential(new DriveStraightRampDownOnlyCommand(228), 2.5);

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight ? -90 : 90), 2);
        addParallel(new QuisitorAcquireCommand(), .5);
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(10), 15));
        addSequential(new DriveStraightRampDownOnlyCommand(112), 2);
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight ? -135 : 135), 1);
        addParallel(new DrivetrainMoveInchesEncoderCommand(13 + 12, .4));

        addSequential(new LiftMoveToHeightCommand(60), 4);
        // Score 1st cube
        addParallel(new QuisitorMoveSpeedCommand(-.75), 0.5);

        // Move back and get ready to grab cube
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 5));
        addSequential(new DrivetrainMoveInchesEncoderCommand(10, -0.3));

        // Move towards cube to grab
        addSequential(new QuisitorOpenCommand());
        addSequential(new LiftMoveToBottomCommand(), 2);
        addParallel(new QuisitorAcquireCommand());

        addParallel(new DrivetrainMoveInchesEncoderCommand(34, 0.5));
        addSequential(new WaitUntilCubeDetectedCommand());
        addSequential(new DrivetrainStopCommand());
        addSequential(new QuisitorCloseCommand());
        // Give it a little bit of time to acquire
        addSequential(new QuisitorAcquireCommand(), 0.5);

        addParallel(new LiftMoveToHeightCommand(65), 5);
        addSequential(new WaitUntilLiftGoesBelowHeightCommand(20));
//        addSequential(new DrivetrainMoveInchesEncoderCommand(10, 0.25), 1);
        addSequential(new DriveStraightPIDCommand(10, 0.25), 1);

        // Score 2nd cube
        addSequential(new QuisitorMoveSpeedCommand(-0.75), 0.25);

        // Get to the 3rd cube
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 5));
        addSequential(new QuisitorOpenCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(10 + 12, -0.3));
        addSequential(new LiftMoveToBottomCommand(), 3);
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight ? -210 : 210), 1);
        addParallel(new QuisitorAcquireCommand());
        addParallel(new DrivetrainMoveInchesEncoderCommand(20, 0.4));
        addSequential(new WaitUntilCubeDetectedCommand());
        addSequential(new DrivetrainStopCommand());
        addSequential(new QuisitorCloseCommand());
        addSequential(new QuisitorAcquireCommand(), 0.5);


    }
}
