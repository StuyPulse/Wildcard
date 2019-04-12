package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DrivetrainLowGearCommand extends InstantCommand {

    public DrivetrainLowGearCommand() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        if (!Robot.getInstance().isAutonomous())
            Robot.drivetrain.lowGearShift();
    }

}
