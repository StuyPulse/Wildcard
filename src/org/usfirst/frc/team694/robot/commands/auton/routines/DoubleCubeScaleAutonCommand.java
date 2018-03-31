package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleCubeScaleAutonCommand extends CommandGroup {

    public DoubleCubeScaleAutonCommand(boolean isRight) {
        addSequential(new SingleCubeScaleAutonChooserCommand());
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -150.0 : 150.0));
        addSequential(new LiftMoveToBottomCommand());
        addParallel(new QuisitorAcquireCommand(), 3.0);
        addSequential(new DrivetrainMoveInchesEncoderCommand(64.0, .25));
        addSequential(new DrivetrainMoveInchesEncoderCommand(64.0, -.25));
        addSequential(new LiftMoveToHeightCommand(83.0));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -45.0 : 45.0));
        addSequential(new QuisitorDeacquireCommand());
    }
}
