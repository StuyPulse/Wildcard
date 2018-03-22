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
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Totally tentative swerving command
 */
public class SameSideScaleAutonCommand extends AutonCommandGroup {

    private static final double DISTANCE_TOTAL = 296;

    public SameSideScaleAutonCommand() {
        boolean isRight = Robot.getIsRobotOnRight();
//        DriveStraightWithRampingCommand rampCommand = new DriveStraightWithRampingCommand(DISTANCE_TOTAL);

        addSequential(new PrintCommand("[SameSideScale] Same Side!"));
        if (Robot.USING_MILDCARD_LIFT_WITH_1_MOTOR) {
            addSequential(new LiftMoveToHeightCommand(40 + 89 - RobotMap.MIN_HEIGHT_OF_LIFT));
        }

        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL);
        driveCommand.addSpeedChange(0, 0.75);
        driveCommand.addTurn(130, isRight ? -45 : 45);
        driveCommand.addTurn(130 + 120, isRight ? 5 : -5);
//        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand, 0, 0.75));
//        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 130, -45));
//        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 130 + 120, 5));
        if (!Robot.USING_MILDCARD_LIFT_WITH_1_MOTOR) {
            addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT), 15));
        }
//        addSequential(rampCommand, 5);
        addSequential(driveCommand, 3.3);

        addSequential(new QuisitorOpenCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(-0.5, 10));
//        addSequential(new LiftMoveToBottomCommand());

        addSequential(new PrintCommand("[SameSideScale] GOGOGOGOGOGOOGOGOGOGO"));
        addSequential(new WaitCommand(5));

        addSequential(new PrintCommand("[SameSideScale] Same Side STOP!: " + Robot.getIsRobotOnRight()));

        addSequential(new ScaleGrabCubeAfterScoringCommand(isRight));
//        addSequential(new ScaleScoreSecondTimeCommand(true));

    }
}
