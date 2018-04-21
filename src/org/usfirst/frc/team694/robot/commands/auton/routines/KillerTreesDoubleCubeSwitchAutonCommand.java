package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class KillerTreesDoubleCubeSwitchAutonCommand extends CommandGroup {

    public KillerTreesDoubleCubeSwitchAutonCommand(boolean isSwitchRight) {
        addSequential(new DriveStraightRampDownOnlyCommand(228), 4);
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isSwitchRight ? 90 : -90));
        addSequential(new DriveStraightRampDownOnlyCommand(143));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isSwitchRight ? 135 : -135));
        addParallel(new DrivetrainMoveInchesEncoderCommand(13, .4));
        addSequential(new LiftMoveToHeightCommand(60));
        addParallel(new QuisitorDeacquireCommand(), 0.5);
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToBottomCommand(), 5));
        addSequential(new DrivetrainMoveInchesEncoderCommand(10, -0.3));
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(10, 0.3));
        addSequential(new LiftMoveToHeightCommand(60));
        addParallel(new QuisitorDeacquireCommand(), 0.5);
        addSequential(new DrivetrainMoveInchesEncoderCommand(10, -0.3));
        
    }
}
