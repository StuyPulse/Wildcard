package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightAngleSameSideSwitchAutonCommand extends CommandGroup {

    public RightAngleSameSideSwitchAutonCommand(boolean isRight) {
        // positions robot to score into switch
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(15.0), 10));
        //TODO: Check distances
        addSequential(new DriveStraightRampDownOnlyCommand(176));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight? -90 : 90));
        //TODO: Check distance to move 
        addSequential(new DrivetrainMoveInchesEncoderCommand(15, 0.5));
    }
}
