package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainRotateDegreesGyroCommand extends Command {
    public double targetAngle;
    public double gyroAngle;
    public boolean targetReached;
    
    public DrivetrainRotateDegreesGyroCommand(double degrees) {
        requires(Robot.drivetrain);
        //We need a drivetrain!
        targetAngle = degrees;
    }

    protected void initialize() {
        Robot.drivetrain.resetGyro();
        SmartDashboard.getNumber("Target Angle", 0);
    }

    protected void execute() {
        Robot.drivetrain.arcadeDrive(0, Math.signum(gyroAngle) * .5);
        gyroAngle = Robot.drivetrain.getGyroAngle();
        System.out.println("Gyro: " + gyroAngle + ". Target: " + targetAngle);
        if (Math.abs(targetAngle) - Math.abs(gyroAngle) < 1) {
            Robot.drivetrain.tankDrive(0,0);
            targetReached = true;
        }
    } 

    protected boolean isFinished() {
        return targetReached;
    }

    protected void end() {
        Robot.drivetrain.resetGyro();
    }

    protected void interrupted() {
    }
}