package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.WaitUntilCubeDetectedCommand;
import org.usfirst.frc.team694.robot.commands.auton.WaitUntilLiftGoesToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class KillerTreesDoubleCubeSwitchAutonCommand extends CommandGroup {

    public KillerTreesDoubleCubeSwitchAutonCommand(boolean isRobotRight) {

        // Go first distance and turn
        addSequential(new DriveStraightRampDownOnlyCommand(228), 2.5);
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight ? -90 : 90), 1);
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(10), 15));
        addSequential(new DriveStraightRampDownOnlyCommand(143 - 2*12 - 10), 2.5);
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight ? -135 : 135), 1);
        addParallel(new DrivetrainMoveInchesEncoderCommand(13 + 12, .4));

        addSequential(new LiftMoveToHeightCommand(60));
        // Score 1st cube
        addParallel(new QuisitorDeacquireCommand(), 0.5);

        // Move back and get ready to grab cube
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 5));
        addSequential(new DrivetrainMoveInchesEncoderCommand(10, -0.3));

        // Move towards cube to grab
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand());

        addParallel(new DrivetrainMoveInchesEncoderCommand(10, 0.3));
        addSequential(new WaitUntilCubeDetectedCommand());
        addSequential(new DrivetrainStopCommand());
        addSequential(new QuisitorCloseCommand());
        // Give it a little bit of time to acquire
        addSequential(new QuisitorAcquireCommand(), 0.5);

        addParallel(new LiftMoveToHeightCommand(65));
        addSequential(new WaitUntilLiftGoesToHeightCommand(20));
//        addSequential(new DrivetrainMoveInchesEncoderCommand(10, 0.25), 1);
        addSequential(new DriveStraightPIDCommand(10, 0.25), 1);

        // Score 2nd cube
        addParallel(new QuisitorDeacquireCommand(), 0.5);

        // Get to the 3rd cube
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 5));
        addSequential(new QuisitorOpenCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(10, -0.3));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight ? -(180 + 30) : 180 + 30), 1);
        addParallel(new QuisitorAcquireCommand());
        addParallel(new DrivetrainMoveInchesEncoderCommand(20, 0.4));
        addSequential(new WaitUntilCubeDetectedCommand());
        addSequential(new DrivetrainStopCommand());
        addSequential(new WaitCommand(0.5));
        addSequential(new QuisitorStopCommand());

    }
}
