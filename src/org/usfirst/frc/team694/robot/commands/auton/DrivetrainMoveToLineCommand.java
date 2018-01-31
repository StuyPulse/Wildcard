package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives until the robot reaches the line.
 */
public class DrivetrainMoveToLineCommand extends Command {
    private boolean isReached = false; 
    private double distance = 0;
    private double speedMode = 0;
    public DrivetrainMoveToLineCommand(double distance,double speed) {
        speedMode =  ((speed / 0.25) - 1);
        this.distance = distance;
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.resetEncoders();
        Robot.drivetrain.resetLineSensors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.updateSensors();
        Robot.drivetrain.tankDrive((speedMode + 1) * 0.25, (speedMode + 1) * 0.25);
        isReached = Robot.drivetrain.isOnLine((int) speedMode);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isReached || (Math.max(Robot.drivetrain.getRightEncoderDistance(),Robot.drivetrain.getLeftEncoderDistance()) > (distance + 5));
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
