package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorStopCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This auton assumes that we have just grabbed a cube from the exchange
 * without moving back, facing forward on the field 
 * (with the bot's back bumper parallel to the starting line)
 */
public class SwitchPostScoreSecondScoreCommand extends CommandGroup {

    private static final double SWITCH_WIDTH = 189.5;

    // How far our bot's center ends up from the scale's edge (use the edge closest to the edge of the scale)
    private static final double DISTANCE_BOT_CENTER_IS_FROM_SCALE_EDGE = 12 + RobotMap.WIDTH_OF_BOT / 2;

    private static final double DISTANCE_DRIVE = 100;
    private static final double DRIVE_RAMP_TIMEOUT = 2;

    public SwitchPostScoreSecondScoreCommand(boolean isRight) {

        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_DRIVE, RampMode.NO_RAMPING);

        driveCommand.addSpeedChange(0, 1);
        driveCommand.addTurn(0, isRight ? 90 : -90);
        driveCommand.addTurn(35, isRight ? -5 : 5); // overshoot intentionally

        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30), 40));
        addParallel(new ConditionalDistanceEncodersCommand(
                new SideSwitchAutonChooserCommand.QuisitorDeacquireTimeCommand(), 95));
//        addParallel(new ConditionalDistanceEncodersCommand(new SpatulaDeacquireCommand(), 95));
        addSequential(driveCommand, DRIVE_RAMP_TIMEOUT);

        addSequential(new QuisitorStopCommand());
    }
}
