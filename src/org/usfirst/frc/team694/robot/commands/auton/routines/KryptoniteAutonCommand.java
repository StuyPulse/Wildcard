package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class KryptoniteAutonCommand extends CommandGroup {

    public KryptoniteAutonCommand(boolean isRobotRight) {
        // scores first cube into switch
        addSequential(new RightAngleSameSideSwitchAutonCommand(isRobotRight));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));

        //To acquire and score second cube
        /*
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight? -180 : 180));
        //TODO: Check distance
        addSequential(new DrivetrainMoveInchesEncoderCommand(50, -0.8));
        //TODO: Check angle
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight? -135 : 135));
        addParallel(new QuisitorOpenCommand());
        //TODO: Check distance
        addSequential(new DrivetrainMoveInchesEncoderCommand(10, 0.5));
        addSequential(new QuisitorAcquireCommand(), 0.5);
        addSequential(new QuisitorCloseCommand());
        addSequential(new LiftMoveToHeightCommand(15));
        addSequential(new DrivetrainMoveInchesEncoderCommand(6, 0.5));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight? -180 : 180));
        addSequential(new QuisitorDeacquireCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(6, -0.5));
        addSequential(new LiftMoveToBottomCommand());

        //To acquire third cube and turn to scale
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight? -135 : 135));
        addParallel(new QuisitorOpenCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(10, 0.5));
        addSequential(new QuisitorAcquireCommand(), 0.5);
        addSequential(new QuisitorCloseCommand());
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight? -90 : 90));
        */
    }
}
