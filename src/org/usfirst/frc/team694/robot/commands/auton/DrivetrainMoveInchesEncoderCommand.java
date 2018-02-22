package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainMoveInchesEncoderCommand extends Command {
    private double distance;
    private double speed;

    private double startDistance;

    public DrivetrainMoveInchesEncoderCommand(double speed, double distance) {
        requires(Robot.drivetrain);
        this.speed = speed;
        this.distance = distance;
    }

    protected void initialize() {
//        Robot.drivetrain.resetEncoders();
        // no reset encoders. sad reaccs :(
        startDistance = Robot.drivetrain.getEncoderDistance();
        System.out.println("DrivetrainMoveInches] start: " + startDistance);
    }

    protected void execute() {
        Robot.drivetrain.tankDrive(speed, speed);
    }

    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getEncoderDistance() - startDistance) >= distance;
    }

    protected void end() {
        System.out.println("[DrivetrainMoveInches] end: " + Robot.drivetrain.getEncoderDistance());
        Robot.drivetrain.stop();
    }
}