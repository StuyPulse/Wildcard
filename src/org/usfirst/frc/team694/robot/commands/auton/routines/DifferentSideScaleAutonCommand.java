package org.usfirst.frc.team694.robot.commands.auton.routines;


import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;

import edu.wpi.first.wpilibj.command.PrintCommand;

public class DifferentSideScaleAutonCommand extends AutonCommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    private static final double DISTANCE_TOTAL = 410;//450;

    public DifferentSideScaleAutonCommand() {

//        DriveStraightWithRampingCommand rampCommand = new DriveStraightWithRampingCommand(DISTANCE_TOTAL);
        // Uncomment for no ramp down:
        addSequential(new PrintCommand("[DifferentSideScale] Different Side!"));
//        DriveStraightWithRampingCommand rampCommand = new DriveStraightRampUpOnlyCommand(DISTANCE_TOTAL);

        if (Robot.USING_MILDCARD_LIFT_WITH_1_MOTOR) {
            addSequential(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT - 18));
        }
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL);

        driveCommand.addSpeedChange(76, 0.4);
        driveCommand.addTurn(76, -140);
        driveCommand.addSpeedChange(76 + 100, 1);
        driveCommand.addTurn(76 + 200, -90);
        driveCommand.addSpeedChange(76 + 200, 0.3);
        driveCommand.addTurn(76 + 260, 15);

//        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 76, -140)); // First turn
//        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand,  76, 0.4));     // Slow down for turn
//        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand,  76 + 100, 1)); // Normal speed after turn
//        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 76 + 200, -90)); // Realign to prevent overshooting
//        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand,  76 + 200, 0.3)); // Slow speed before last turn
//        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 76 + 260, 15));  // Final turn // 300
        //addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT), DISTANCE_TOTAL - 200));
        if (!Robot.USING_MILDCARD_LIFT_WITH_1_MOTOR) {
            addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT - 18), DISTANCE_TOTAL - 200));
        }
        addSequential(driveCommand);

        addSequential(new QuisitorOpenCommand());

    }

}