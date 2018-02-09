package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainMoveToLineCommand extends Command {
    private boolean isReached = false; 
    private double distance = 0;
    private double speed = 0;
    public DrivetrainMoveToLineCommand(double distance,double speed) {
        this.distance = distance;
        this.speed = speed;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.updateSensors();
        Robot.drivetrain.tankDrive(speed,speed);
        isReached = Robot.drivetrain.isOnLine();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isReached || (Math.max(Robot.drivetrain.getRightEncoderDistance(),Robot.drivetrain.getLeftEncoderDistance()) > (distance + 5));
    }
    //TODO: remove magic number above
    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.resetEncoders();
        Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
