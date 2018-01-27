package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class AcquirerFlipDownCommand extends InstantCommand {

    public AcquirerFlipDownCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.crabArm);
        requires(Robot.acquirer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.acquirer.flipDown();
        Robot.crabArm.retractPush();
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
