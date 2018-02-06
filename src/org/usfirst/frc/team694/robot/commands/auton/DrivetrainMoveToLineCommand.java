package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainMoveToLineCommand extends Command {
    private boolean isReached = false; 
    private double distance = 0;
    private double speed = 1;
    public DrivetrainMoveToLineCommand(double distance,double speed) {
        this.distance = distance;//represents length in inches to go, just in case line sensing fails.
        // Use requires() here to declare subsystem dependencies
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
        Robot.drivetrain.tankDrive(speed, speed);
        isReached = Robot.drivetrain.isOnLine((int) speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isReached || (Robot.drivetrain.getEncoderDistance() > distance);
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
