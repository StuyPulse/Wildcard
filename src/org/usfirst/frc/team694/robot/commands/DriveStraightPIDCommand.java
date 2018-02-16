package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightPIDCommand extends PIDCommand {
	double distance;
	double speed;
	double output;
	public DriveStraightPIDCommand(double distance, double speed) {
		super(0,0,0);
		requires(Robot.drivetrain);
		this.distance = distance;
		this.speed = speed;
	}

	protected void initialize() {
		Robot.drivetrain.resetGyro();
		Robot.drivetrain.resetEncoders();
		this.getPIDController().setPID(
				SmartDashboard.getNumber("DriveStraightPID P", 0), 
				SmartDashboard.getNumber("DriveStraightPID I", 0), 
				SmartDashboard.getNumber("DriveStraightPID D", 0)
				);
	}

	protected void execute() {
		System.out.println("[DriveStraightPIDCommand] angle:" + returnPIDInput());
	}

	protected boolean isFinished() {
		return Robot.drivetrain.getEncoderDistance() >= distance;
	}

	protected void end() {
		Robot.drivetrain.tankDrive(0, 0);
	}

	protected void interrupted() {
	}

	@Override
	protected double returnPIDInput() {
		return Robot.drivetrain.getGyroAngle();
	}
	@Override
	protected void usePIDOutput(double output) {
		this.output = output;
		Robot.drivetrain.tankDrive(speed + output , speed - output);
	}
}
