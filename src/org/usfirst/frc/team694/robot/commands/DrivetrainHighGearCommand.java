package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DrivetrainHighGearCommand extends InstantCommand {

    public DrivetrainHighGearCommand() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        if (!Robot.getInstance().isAutonomous())
            Robot.drivetrain.highGearShift();
    }

}
