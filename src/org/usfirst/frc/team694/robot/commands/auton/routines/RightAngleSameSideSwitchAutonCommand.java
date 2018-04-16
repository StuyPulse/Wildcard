package org.usfirst.frc.team694.robot.commands.auton.routines;

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
        addSequential(new DriveStraightRampDownOnlyCommand(176));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotRight? -90 : 90));
        //TODO: Check distance to move 
        addSequential(new DrivetrainMoveInchesEncoderCommand(15, 0.5));
        addParallel(new QuisitorDeacquireCommand(),0.5);
        addSequential(new DrivetrainMoveInchesEncoderCommand(15.0, -0.8));
    }
}
