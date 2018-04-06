package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SameSideDoubleCubeSwitchAutonCommand extends CommandGroup {

    public SameSideDoubleCubeSwitchAutonCommand() {
       addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(15), 50));
       addSequential(new DrivetrainMoveInchesEncoderCommand(176, 0.8));
       addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-90));
       addSequential(new DrivetrainMoveInchesEncoderCommand(15, 0.8)); //TODO: Check distance
       addSequential(new QuisitorDeacquireCommand());
       addSequential(new DrivetrainMoveInchesEncoderCommand(-5,0.2));
       addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
       addSequential(new LiftMoveToBottomCommand());
       addSequential(new DrivetrainMoveInchesEncoderCommand(70, 0.4));
       addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-90));
       addSequential(new DrivetrainMoveInchesEncoderCommand(15, 0.3));
       addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-180));
       addSequential(new QuisitorOpenCommand());
       addSequential(new QuisitorAcquireCommand());
       addSequential(new DrivetrainMoveInchesEncoderCommand(6, 0.8));
       addSequential(new QuisitorCloseCommand());
       addSequential(new QuisitorStopCommand());
       addSequential(new LiftMoveToHeightCommand(30));
       addSequential(new QuisitorDeacquireCommand());
       
    }
}
