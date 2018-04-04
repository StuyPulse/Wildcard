package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeScaleAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleCubeScaleAutonCommand extends CommandGroup {

    public DoubleCubeScaleAutonCommand(boolean isRight) {
        addSequential(new SingleCubeScaleAutonChooserCommand());
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -150.0 + 5 : 150.0 - 5), 0.5 + .5);
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand());
        addSequential(new DriveStraightRampDownOnlyCommand(71 + 12), 2);
        //addSequential(new DrivetrainMoveInchesEncoderCommand(64.0 + 7, .6), 2 + .5);
        addSequential(new QuisitorCloseCommand());
        addSequential(new QuisitorAcquireCommand(), .5);
        addParallel(new QuisitorAcquireCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(64.0 - 25 + 5, -1), 2 + .5);
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -60.0 - 25: 60.0 + 25), .5 + .3);
        addSequential(new QuisitorStopCommand());
        
        addSequential(new LiftMoveToHeightCommand(83.0));
        addSequential(new QuisitorDeacquireCommand(), 1.5);
        addSequential(new LiftMoveToBottomCommand());
    }
}
