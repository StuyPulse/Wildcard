package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 * Totally tentative swerving command
 */
public class SameSideScaleAutonCommand extends CommandGroup {

    private static final double DISTANCE_TOTAL = 296;

    public SameSideScaleAutonCommand() {
        DriveStraightWithRampingCommand rampCommand = new DriveStraightWithRampingCommand(DISTANCE_TOTAL);
        // Uncomment for no ramp down:
//        DriveStraightWithRampingCommand rampCommand = new DriveStraightRampUpOnlyCommand(DISTANCE_TOTAL);

        addSequential(new PrintCommand("[SameSideScale] Same Side!"));
        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand, 0, 0.75));
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 130, -45));
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 130 + 120, 5));
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT), 0));
        addSequential(rampCommand, 5);

        addSequential(new GrabberOpenCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(-0.5, 10));
//        addSequential(new LiftMoveToBottomCommand());

        //        addSequential(new DrivetrainRotateDegreesPIDCommand(-165));

//        addParallel(new CrabArmAcquireCommand());
//        addSequential(new DrivetrainMoveInchesEncoderCommand(0.5,10));

        addSequential(new ScaleGrabCubeAfterScoringCommand(true));
        addSequential(new ScaleScoreSecondTimeCommand(true));

        
        // Move lift when we're kinda close
//        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT), DISTANCE_TOTAL - 100));
//        addSequential(new DrivetrainRampSwerveCommand(DISTANCE_TOTAL, DISTANCE_TO_SWERVE, -30));

//        addSequential(new GrabberOpenCommand());

//        addSequential(new LiftMoveToBottomCommand());
    }
}
