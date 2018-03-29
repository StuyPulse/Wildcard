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
public class DoubleCubeSameSideScaleAutonCommand extends CommandGroup {

    public DoubleCubeSameSideScaleAutonCommand() {
        addSequential(new SingleCubeSameSideScaleAutonCommand());
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(Robot.isRobotStartingOnRight() ? -150.0 : 150.0));
        addSequential(new LiftMoveToBottomCommand());
        addSequential(new QuisitorAcquireCommand(), 3.0);
        addSequential(new DrivetrainMoveInchesEncoderCommand(64.0, .25));
        addSequential(new DrivetrainMoveInchesEncoderCommand(64.0, -.25));
        addSequential(new LiftMoveToHeightCommand(83.0));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(Robot.isRobotStartingOnRight() ? -45.0 : 45.0));
        addSequential(new QuisitorDeacquireCommand());
    }
}
