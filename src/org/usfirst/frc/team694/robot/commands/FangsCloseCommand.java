package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class FangsCloseCommand extends InstantCommand {

    public FangsCloseCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.quisitor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.quisitor.fangsClose();
    }
}
