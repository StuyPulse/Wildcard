package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivetrainRotateDegreesPIDCommand extends PIDCommand {
    public double targetAngle;
    public DrivetrainRotateDegreesPIDCommand(double targetAngle) {
        super(0,0,0);
        this.targetAngle = targetAngle;
        setSetpoint(targetAngle);
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.resetGyro();
        this.getPIDController().setPID(
                SmartDashboard.getNumber("RotateDegreesPID P", 0), 
                SmartDashboard.getNumber("RotateDegreesPID I", 0), 
                SmartDashboard.getNumber("RotateDegreesPID D", 0)
                );
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("[RotateDegreesPIDCommand] angle:" + returnPIDInput());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getGyroAngle() - targetAngle) <= 0.01;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
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