package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DrivetrainHighGearCommand extends InstantCommand {

    public DrivetrainHighGearCommand() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.drivetrain.highGearShift();
    }

}
