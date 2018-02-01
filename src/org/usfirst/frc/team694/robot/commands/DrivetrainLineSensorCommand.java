package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainLineSensorCommand extends Command {
    double speed = 0.25;
    double offset = 0;
    public DrivetrainLineSensorCommand(double speed, double offset) {
        this.speed = speed;
        this.offset = offset;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.isOnLine((int) Math.round(speed / 0.25) + 1);
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.setEncoders(offset);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
