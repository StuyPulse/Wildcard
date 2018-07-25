package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

public class DrivetrainRotateDegreesGyroCommand extends DrivetrainRotateCommand {
    
    private static final double CLOSE_ENOUGH_THRESHOLD = 4;

    public DrivetrainRotateDegreesGyroCommand(double targetAngle) {
        super(targetAngle);
    }

    @Override
    protected void execute() {
        double speed = Math.signum(-1 * getDeltaAngle());
        Robot.drivetrain.tankDrive(speed, -1 * speed);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(getDeltaAngle()) < CLOSE_ENOUGH_THRESHOLD;
    }

    @Override
    protected void end() {
        super.end();
        Robot.drivetrain.stop();
    }

}