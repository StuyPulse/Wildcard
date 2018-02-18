package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftHoldHeightCommand extends Command {
    private double currentHeight;
    
    public LiftHoldHeightCommand() {
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        currentHeight = Robot.lift.getLiftHeight();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.lift.setHeight(currentHeight);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.lift.stop();
    }
}
