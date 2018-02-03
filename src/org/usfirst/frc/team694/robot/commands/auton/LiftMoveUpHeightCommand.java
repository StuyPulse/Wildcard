package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftMoveUpHeightCommand extends Command {
    
    public double height;

    public LiftMoveUpHeightCommand(double height) {
        requires(Robot.lift);
        this.height = height;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.lift.resetEncoders();
        Robot.lift.setBrakeOff();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.lift.goUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.lift.getLiftHeight() >= height;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.lift.setBrakeOn();
        Robot.lift.resetEncoders();
        Robot.lift.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
