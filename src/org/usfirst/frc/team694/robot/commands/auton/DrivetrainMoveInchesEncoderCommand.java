package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainMoveInchesEncoderCommand extends Command {
    private double distance;
    private double speed;

    private double startDistance;

    public DrivetrainMoveInchesEncoderCommand(double speed, double distance) {
        this.speed = speed;
        this.distance = distance;
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        startDistance = Robot.drivetrain.getEncoderDistance();
        System.out.println("[DrivetrainMoveInches] start: " + startDistance);
    }

    @Override
    protected void execute() {
        Robot.drivetrain.tankDrive(speed, speed);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getEncoderDistance() - startDistance) >= distance;
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
        System.out.println("[DrivetrainMoveInches] end: " + Robot.drivetrain.getEncoderDistance());
    }
}