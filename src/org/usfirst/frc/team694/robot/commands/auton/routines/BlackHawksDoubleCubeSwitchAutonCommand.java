package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.WaitUntilCubeDetectedCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * GOOD ONE
 */

public class BlackHawksDoubleCubeSwitchAutonCommand extends CommandGroup {

    public BlackHawksDoubleCubeSwitchAutonCommand(boolean isRobotRight, boolean isSwitchSameSide) {

        // Drive to rough scoring position
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(5), 15));
        addSequential(new DriveStraightWithRampingCommand(235 - 14 + 7 + 5), 2.5);
        addSequential(new WaitCommand(0.5));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight ? -90 : 90), 1.4);
        addParallel(new LiftMoveToHeightCommand(10));
        addParallel(new QuisitorAcquireCommand(), 0.5);
        if (isSwitchSameSide){
            addSequential(new DriveStraightWithRampingCommand(15 + 18 + 5 + 6.5 + 9 - 11));
        } else {
            addSequential(new DriveStraightWithRampingCommand(121));
        }
        //addSequential(new DriveStraightWithRampingCommand(isSwitchSameSide ? 15 + 24 + 5 : 121));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight ? -180 : 180), 1.5);

        // Score 1st cube
        //drive forward
        addParallel(new LiftMoveToHeightCommand(30));
        addSequential(new DrivetrainMoveInchesEncoderCommand(13 + 3 + 3 + 5, .3 + 0.1),2);
        addSequential(new QuisitorDeacquireCommand(), 0.5);

        // Grab 2nd cube
        //backup parallel 6in
        addSequential(new DrivetrainMoveInchesEncoderCommand(6, -1));
        addSequential(new QuisitorOpenCommand());
        addSequential(new LiftMoveToBottomCommand());
        addParallel(new QuisitorAcquireCommand(), 0.5);

        addParallel(new DrivetrainMoveInchesEncoderCommand(10, 0.5));
        addSequential(new WaitUntilCubeDetectedCommand());
        addSequential(new DrivetrainStopCommand());

        addSequential(new QuisitorCloseCommand());
        addSequential(new QuisitorAcquireCommand(), 0.5);
        addSequential(new QuisitorStopCommand());

        // Score that 2nd cube
        addSequential(new LiftMoveToHeightCommand(30));
        //forward
        addSequential(new DrivetrainMoveInchesEncoderCommand(5, .75));
        addSequential(new QuisitorMoveSpeedCommand(-0.6), 0.5);
        addSequential(new DrivetrainMoveInchesEncoderCommand(20, -.75));
        addParallel(new LiftMoveToBottomCommand());
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isSwitchSameSide ? -145 : 145), 1);
        addParallel(new QuisitorAcquireCommand(), 3);
        addParallel(new DrivetrainMoveInchesEncoderCommand(20 + 3, 0.3 + 0.1));
        addSequential(new WaitUntilCubeDetectedCommand());
        addSequential(new DrivetrainStopCommand());
        addSequential(new QuisitorStopCommand());

        // Go to other side

//        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(15), 50));
//        addSequential(new DrivetrainMoveInchesEncoderCommand(176, 0.8));
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight? 90 : -90));
//        addSequential(new DrivetrainMoveInchesEncoderCommand(15, 0.8)); //TODO: Check distance
//        addSequential(new QuisitorDeacquireCommand(), 0.5);
//        addSequential(new DrivetrainMoveInchesEncoderCommand(-5, 0.2));
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
//
//        // Grab the second cube
//        addSequential(new LiftMoveToBottomCommand());
//        addSequential(new DrivetrainMoveInchesEncoderCommand(70, 0.4));
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight? 90 : -90));
//        addSequential(new DrivetrainMoveInchesEncoderCommand(15, 0.3));
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight? 180 : -180));
//        addSequential(new QuisitorOpenCommand());
//        addParallel(new QuisitorAcquireCommand());
//        addSequential(new DrivetrainMoveInchesEncoderCommand(6, 0.8));
//        addSequential(new QuisitorCloseCommand());
//        addSequential(new QuisitorStopCommand());
//
//        // Score 2nd time
//        addSequential(new LiftMoveToHeightCommand(30));
//        addSequential(new QuisitorDeacquireCommand());

    }
}
