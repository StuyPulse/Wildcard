package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.PrintCommand;

public class ScaleScoreSecondTimeCommand extends AutonCommandGroup {

    public ScaleScoreSecondTimeCommand(boolean isRightSide) {
        addSequential(new PrintCommand("[ScaleScoreSecondTime] START"));

        addSequential(new QuisitorCloseCommand());

        // Rotate back to where we were before grabbing the cube
        if (isRightSide) {
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-165));
        } else {
            addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(165));
        }

        addSequential(new DrivetrainMoveInchesEncoderCommand(20, -0.3), 2);

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        // Height used to be 89 - minheight
        addSequential(new LiftMoveToHeightCommand(83 - RobotMap.MIN_HEIGHT_OF_LIFT));

        addSequential(new DrivetrainMoveInchesEncoderCommand(20, 0.3), 3);

        addSequential(new QuisitorOpenCommand());
    }
}
