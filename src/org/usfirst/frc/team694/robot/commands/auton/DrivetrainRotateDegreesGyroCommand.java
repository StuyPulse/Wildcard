package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainRotateDegreesGyroCommand extends Command {
    public double targetAngle;

    public DrivetrainRotateDegreesGyroCommand(double targetAngle) {
        this.targetAngle = targetAngle;
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        Robot.drivetrain.resetGyro();
        SmartDashboard.getNumber("Target Angle", 0);
    }

    @Override
    protected void execute() {
        Robot.drivetrain.arcadeDrive(0, Math.signum(Robot.drivetrain.getGyroAngle()) * .5);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(targetAngle) - Math.abs(Robot.drivetrain.getGyroAngle()) < 1;
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
        Robot.drivetrain.resetGyro();
    }

}