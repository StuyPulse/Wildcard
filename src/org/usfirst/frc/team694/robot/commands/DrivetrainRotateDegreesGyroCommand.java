package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivetrainRotateDegreesGyroCommand extends Command {
    public double targetAngle;
    public double gyroAngle;
    public boolean targetReached;
    public DrivetrainRotateDegreesGyroCommand() {
        requires(Robot.drivetrain);
        //We need a drivetrain!
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.resetGyro();
        SmartDashboard.getNumber("Target Angle",0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.arcadeDrive(0, Math.signum(gyroAngle) * .5);
        gyroAngle = Robot.drivetrain.getGyroAngle();
        System.out.println("Gyro: " + gyroAngle + ". Target: " + targetAngle);
        if (Math.abs(targetAngle) - Math.abs(gyroAngle) < 1) {
            Robot.drivetrain.tankDrive(0,0);
            targetReached = true;
        }
    } 

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return targetReached;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.resetGyro();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
