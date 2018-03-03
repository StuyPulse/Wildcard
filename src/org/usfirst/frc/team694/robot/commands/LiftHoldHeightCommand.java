package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
//TODO: Is this needed?
public class LiftHoldHeightCommand extends Command {
    private double currentHeight;

    public LiftHoldHeightCommand() {
//        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        currentHeight = Robot.lift.getLiftHeight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.lift.setHeight(currentHeight);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.lift.stop();
    }
}
