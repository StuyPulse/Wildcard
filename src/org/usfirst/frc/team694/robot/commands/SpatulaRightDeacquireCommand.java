package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpatulaRightDeacquireCommand extends Command {

    public SpatulaRightDeacquireCommand() {
        requires(Robot.spatula);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        System.out.println("SPATULA RIGHT DEACQUIRE START");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.spatula.rightSpatulaDeacquire();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.spatula.stop();
        System.out.println("SPATULA RIGHT DEACQUIRE STOP");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
