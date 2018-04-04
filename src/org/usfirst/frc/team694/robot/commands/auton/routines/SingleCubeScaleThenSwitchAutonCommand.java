package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeScaleAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SingleCubeScaleThenSwitchAutonCommand extends CommandGroup {

    public SingleCubeScaleThenSwitchAutonCommand(boolean isRobotOnRight) {
        addSequential(new SingleCubeScaleAutonChooserCommand()); 
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotOnRight ? -150.0 : 150.0));
        addParallel(new LiftMoveToBottomCommand());
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand());
        addSequential(new DriveStraightRampDownOnlyCommand(71 + 12), 2);
        addSequential(new LiftMoveToHeightCommand(30.0));
        addSequential(new QuisitorDeacquireCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(10, -.7));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRobotOnRight ? -135.0 : 135.0));
    }
}
