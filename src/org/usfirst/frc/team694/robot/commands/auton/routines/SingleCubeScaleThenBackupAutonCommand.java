package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SingleCubeScaleThenBackupAutonCommand extends CommandGroup {

    public SingleCubeScaleThenBackupAutonCommand(boolean isRight) {
        
        addSequential(new SingleCubeSameSideScaleAutonCommand(isRight));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-45));
        addSequential(new DrivetrainMoveInchesEncoderCommand(131.5, -0.75));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        addSequential(new DrivetrainMoveInchesEncoderCommand(20, -0.3));
        
    }
}
