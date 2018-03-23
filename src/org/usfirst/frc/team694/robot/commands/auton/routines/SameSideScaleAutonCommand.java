package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;

import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 * Totally tentative swerving command
 */
public class SameSideScaleAutonCommand extends AutonCommandGroup {

    private static final double DISTANCE_TOTAL = 296;

    public SameSideScaleAutonCommand() {
        boolean isRight = Robot.isRobotStartingOnRight();

        addSequential(new PrintCommand("[SameSideScale] Same Side!"));

        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL);
 
        driveCommand.addSpeedChange(0, 0.75);
        driveCommand.addTurn(130, isRight ? -45 : 45);
        driveCommand.addTurn(130 + 120, isRight ? 5 : -5);

        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT), 15));

        addSequential(driveCommand, 3.3);

        addSequential(new QuisitorOpenCommand());

        addSequential(new DrivetrainMoveInchesEncoderCommand(-0.5, 10));

        addSequential(new PrintCommand("[SameSideScale] Same Side STOP!: " + isRight));

        addSequential(new ScaleGrabCubeAfterScoringCommand(isRight));
//        addSequential(new ScaleScoreSecondTimeCommand(true));

    }
}
