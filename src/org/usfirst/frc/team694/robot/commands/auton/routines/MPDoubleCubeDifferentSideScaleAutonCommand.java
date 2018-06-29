package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMotionProfileJaciEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MPDoubleCubeDifferentSideScaleAutonCommand extends CommandGroup {

    public MPDoubleCubeDifferentSideScaleAutonCommand(boolean isRight) {
        addSequential(new DrivetrainMotionProfileJaciEncoderCommand(isRight ? "hi Person" : "bye person"));
        addSequential(new LiftMoveToHeightCommand(83));
        addSequential(new QuisitorDeacquireCommand());
        addSequential(new LiftMoveToBottomCommand());
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -180 : 180));
        addParallel(new QuisitorOpenCommand());
        addSequential(new DrivetrainMotionProfileJaciEncoderCommand(isRight ? "hi other person" : "bye other person"));
        addParallel(new QuisitorAcquireCommand());
        addSequential(new QuisitorCloseCommand());
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        addSequential(new DrivetrainMotionProfileJaciEncoderCommand(isRight ? "hi third person" : "bye third person"));
        addSequential(new LiftMoveToHeightCommand(83));
        addSequential(new QuisitorDeacquireCommand());
        addSequential(new LiftMoveToBottomCommand());    
    }
}
