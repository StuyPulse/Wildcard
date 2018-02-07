package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrivetrainRotateDegreesPIDCommand extends PIDCommand {
    public double targetAngle;
    public DrivetrainRotateDegreesPIDCommand(double targetAngle) {
        super(0,0,0);
        this.targetAngle = targetAngle;
        setSetpoint(targetAngle);
        requires(Robot.drivetrain);
    }

    protected void initialize() {
        Robot.drivetrain.resetGyro();
        this.getPIDController().setPID(
                SmartDashboard.getNumber("RotateDegreesPID P", 0), 
                SmartDashboard.getNumber("RotateDegreesPID I", 0), 
                SmartDashboard.getNumber("RotateDegreesPID D", 0)
                );
    }

    protected void execute() {
        System.out.println("[RotateDegreesPIDCommand] angle:" + returnPIDInput());
    }

    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getGyroAngle() - targetAngle) <= 0.01;
    }

    protected void end() {
        Robot.drivetrain.tankDrive(0, 0);
    }

    protected void interrupted() {
    }

    @Override
    protected double returnPIDInput() {
        // TODO Auto-generated method stub
        return Robot.drivetrain.getGyroAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.drivetrain.tankDrive(output, -output);
    }
}