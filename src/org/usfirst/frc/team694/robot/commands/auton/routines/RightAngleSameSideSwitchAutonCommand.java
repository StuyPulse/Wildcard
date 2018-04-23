package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightAngleSameSideSwitchAutonCommand extends CommandGroup {

    public RightAngleSameSideSwitchAutonCommand(boolean isRobotRight) {
        // scores one cube into the switch
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(15.0), 10));
        //TODO: Check distances
        addSequential(new DriveStraightRampDownOnlyCommand(176 - 20));

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight? -90 : 90));
        //TODO: Check distance to move 
        addParallel(new LiftMoveToHeightCommand(30));
        addSequential(new DrivetrainMoveInchesEncoderCommand(15 + 7, 0.3), 3);
        addSequential(new QuisitorDeacquireCommand(),0.5);
        addSequential(new DrivetrainMoveInchesEncoderCommand(15.0, -0.8));
        addSequential(new LiftMoveToBottomCommand());
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
    }
}
