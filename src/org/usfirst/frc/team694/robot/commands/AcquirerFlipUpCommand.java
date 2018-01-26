package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AcquirerFlipUpCommand extends Command {

    public AcquirerFlipUpCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.acquirer)
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.acquirer.flipUp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
