package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainMoveInchesEncoderCommand extends Command {
    public double targetDistance;
    public double driveSpeed;
    
    public DrivetrainMoveInchesEncoderCommand(double speed, double distance) {
        requires(Robot.drivetrain);
        speed = driveSpeed;
        distance = targetDistance;
    }

    protected void initialize() {
        Robot.drivetrain.resetEncoders();
    }

    protected void execute() {
        Robot.drivetrain.tankDrive(driveSpeed, driveSpeed);
    }

    protected boolean isFinished() {
        return Robot.drivetrain.getEncoderDistance() >= targetDistance;
    }

    protected void end() {
        Robot.drivetrain.resetEncoders();
      
    }

    protected void interrupted() {
    }
}