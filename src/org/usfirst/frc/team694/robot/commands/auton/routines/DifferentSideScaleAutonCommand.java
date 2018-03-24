package org.usfirst.frc.team694.robot.commands.auton.routines;


import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;

import edu.wpi.first.wpilibj.command.PrintCommand;

@Deprecated // I mean not technically deprecated but please don't use this yet
public class DifferentSideScaleAutonCommand extends AutonCommandGroup {
//    private FieldMapInterface quad = Robot.getRobotQuadrant();

    private static final double DISTANCE_TOTAL = 410;//450;

    public DifferentSideScaleAutonCommand() {
        super();

        addSequential(new PrintCommand("[DifferentSideScale] Different Side!"));

        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL);

        driveCommand.addSpeedChange(76, 0.4);
        driveCommand.addTurn(76, -140);
        driveCommand.addSpeedChange(76 + 100, 1);
        driveCommand.addTurn(76 + 200, -90);
        driveCommand.addSpeedChange(76 + 200, 0.3);
        driveCommand.addTurn(76 + 260, 15);

        // While driving, move the lift at a certain distance
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(89 - RobotMap.MIN_HEIGHT_OF_LIFT - 18), DISTANCE_TOTAL - 200));
        addSequential(driveCommand);

        addSequential(new QuisitorOpenCommand());

    }

}