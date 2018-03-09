package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

public class DrivetrainMoveInchesEncoderCommand extends DrivetrainDriveDistanceCommand {
    protected double moveSpeed;

    public DrivetrainMoveInchesEncoderCommand(double speed, double distance) {
        super(distance);
        this.moveSpeed = speed;
    }

    @Override
    protected void execute() {
        Robot.drivetrain.tankDrive(moveSpeed, moveSpeed);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(getDistance()) >= targetDistance;
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
        System.out.println("[DrivetrainMoveInches] end: " + getDistance());
    }

}