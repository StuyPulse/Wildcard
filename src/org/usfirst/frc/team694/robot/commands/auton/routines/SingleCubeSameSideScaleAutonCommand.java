package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class SingleCubeSameSideScaleAutonCommand extends AutonCommandGroup {
    private static final double TOTAL_DISTANCE = 286; //TODO: Not sure about this distance

    public SingleCubeSameSideScaleAutonCommand(boolean isRight) {

        addSequential(new PrintCommand("[SingleCubeSameSideScale] isRight? " + isRight));

        /*
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(TOTAL_DISTANCE);
        driveCommand.addSpeedChange(0, 0.8);
        driveCommand.addTurn(261 - 12, isRight? -45.0 : 45.0);
        //TODO: Make sure this logic is correct
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(82.0), 168));
        addSequential(driveCommand, 3.5);
        */

        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(86.0), 168));
        addSequential(new DriveStraightWithRampingCommand(261));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight? -60 : 60));
        addSequential(new QuisitorDeacquireCommand());
    }
}
