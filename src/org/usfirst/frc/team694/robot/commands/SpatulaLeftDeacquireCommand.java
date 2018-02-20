package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpatulaLeftDeacquireCommand extends Command {

    public SpatulaLeftDeacquireCommand() {
        requires(Robot.spatula);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("SPATULA LEFT DEACQUIRE START");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.spatula.leftSpatulaDeacquire();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.spatula.stop();
        System.out.println("SPATULA LEFT DEACQUIRE STOP");

    }
}
