package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorMoveSpeedCommand;
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

    public SameSideScaleAutonCommand(boolean isRight) {
        super();

        addSequential(new PrintCommand("[SameSideScale] Same Side! "  + isRight));

        // To prevent brownouts
//        addSequential(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT));

        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL);

        driveCommand.addSpeedChange(0, 0.75);
        driveCommand.addTurn(130, isRight ? -45 : 45);     // Initial curve
        driveCommand.addTurn(130 + 120, isRight ? 5 : -5); // Curve back and overcompensate

        // Brownouts
        // Used to be 89
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(20 + 83 - RobotMap.MIN_HEIGHT_OF_LIFT), 15));

        addSequential(driveCommand, 3.3);

//        addSequential(new QuisitorOpenCommand());
        addSequential(new QuisitorMoveSpeedCommand(-0.25), 2);

        addSequential(new DrivetrainMoveInchesEncoderCommand(10,-0.5));

        addSequential(new PrintCommand("[SameSideScale] Same Side STOP!: " + isRight));

//        addSequential(new ScaleGrabCubeAfterScoringCommand(isRight));
//        addSequential(new ScaleScoreSecondTimeCommand(isRight));

    }

}
