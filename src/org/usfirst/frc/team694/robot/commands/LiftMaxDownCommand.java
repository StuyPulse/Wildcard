package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftMaxDownCommand extends Command {

    public LiftMaxDownCommand() {
        requires(Robot.lift);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.lift.setBrakeOff();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.lift.setLiftMotors(-1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.lift.isAtBottom();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.lift.stop();
        Robot.lift.setBrakeOn();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
