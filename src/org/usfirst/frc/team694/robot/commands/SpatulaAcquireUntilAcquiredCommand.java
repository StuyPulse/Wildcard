package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpatulaAcquireUntilAcquiredCommand extends Command {

    public SpatulaAcquireUntilAcquiredCommand() {
        requires(Robot.spatula);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.spatula.acquire();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.spatula.getCurrentStateOfLimitSwitch();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.spatula.stop();
    }
}
