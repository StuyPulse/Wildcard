package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainMoveInchesEncoderCommand extends Command {
    public double distance;
    public double speed;

    public DrivetrainMoveInchesEncoderCommand(double speed, double distance) {
        requires(Robot.drivetrain);
        this.speed = speed;
        this.distance = distance;
    }

    protected void initialize() {
        System.out.println("DrivetrainMoveInches] start");
        Robot.drivetrain.resetEncoders();
    }

    protected void execute() {
        Robot.drivetrain.tankDrive(speed, speed);
    }

    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getEncoderDistance()) >= distance;
    }

    protected void end() {
        System.out.println("DrivetrainMoveInches] end");
        Robot.drivetrain.stop();
    }
}