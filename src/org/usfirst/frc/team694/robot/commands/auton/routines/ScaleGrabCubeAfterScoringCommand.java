package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.PrintCommand;

public class ScaleGrabCubeAfterScoringCommand extends AutonCommandGroup {
 
    public ScaleGrabCubeAfterScoringCommand(boolean isRightSide) {
        addSequential(new PrintCommand("[ScaleGrabCube] START"));
        addSequential(new DrivetrainMoveInchesEncoderCommand(10,-0.3));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        addSequential(new DrivetrainMoveInchesEncoderCommand(10, -0.3));
        addSequential(new LiftMoveToBottomCommand());

        // Relative degrees: +/- 165
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRightSide ? -165 : 165));
        addSequential(new QuisitorOpenCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(20, 0.4), 2);
        addSequential(new QuisitorCloseCommand());
        addSequential(new QuisitorAcquireCommand(),2);

    }
}
