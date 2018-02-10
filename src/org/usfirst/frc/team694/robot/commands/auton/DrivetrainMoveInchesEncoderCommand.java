package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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
        return Math.abs(Robot.drivetrain.getEncoderDistance()) >= targetDistance;
    }

    protected void end() {
        Robot.drivetrain.resetEncoders();
      
    }

    protected void interrupted() {
    }
}