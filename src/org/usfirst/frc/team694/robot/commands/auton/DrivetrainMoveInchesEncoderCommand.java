package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivetrainMoveInchesEncoderCommand extends Command {
    public double targetDistance;
    public double driveSpeed;
    
    public DrivetrainMoveInchesEncoderCommand(double speed, double distance) {
        requires(Robot.drivetrain);
        speed = driveSpeed;
        distance = targetDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.tankDrive(driveSpeed, driveSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getEncoderDistance() >= targetDistance;
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
