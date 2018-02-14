package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightWithRampingWithLineSensorCommand extends DriveStraightWithRampingCommand {
	//double output;
	double offset = 122;
	double valueAtLine = 0;
	boolean hitBefore = false;
	public DriveStraightWithRampingWithLineSensorCommand(double offset) {
		super();
		//System.out.println("hey");
		this.offset = offset;
	}


	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		super.execute();
		//System.out.print("exec");
		if  (Robot.drivetrain.isOnLine(0) && !hitBefore) {
			System.out.println("hit");
			valueAtLine = Robot.drivetrain.getEncoderDistance() - offset;
			hitBefore = true;
			System.out.println(valueAtLine);
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(Math.abs(Robot.drivetrain.getRightEncoderDistance() - targetDistance) <= 1 && !isSet) {
			timeFirstInRange = Timer.getFPGATimestamp();
			isSet = true;
		} else {
			isSet = false;
		}
		System.out.println(Math.abs((Robot.drivetrain.getRightEncoderDistance() - valueAtLine)));
		return Math.abs(Robot.drivetrain.getRightEncoderDistance() - targetDistance) <= 1 && Timer.getFPGATimestamp() - timeFirstInRange > 2;
	}
}
//Math.abs(Robot.drivetrain.getRightEncoderDistance() - targetDistance) <= 1 && 
//0.0086 P 0.04 D 170
// Gyro is 0.04