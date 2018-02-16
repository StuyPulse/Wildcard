package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveStraightWithRampingCommand extends PIDCommand {
	
	protected static final double DRIVE_DISTANCE_THRESHOLD = 1;

	protected double output;
	protected double targetDistance;
	protected double startEncoderValue;
	protected static double angleOutput;
	protected static boolean isSet = false;
	protected static double timeFirstInRange;
	PIDController gyroControl;
	public DriveStraightWithRampingCommand(double targetDistance) {
		super(0, 0, 0);
    	gyroControl = new PIDController(
    			SmartDashboard.getNumber("RotateDegreesPID P", 0), 
    			SmartDashboard.getNumber("RotateDegreesPID I", 0), 
    			SmartDashboard.getNumber("RotateDegreesPID D", 0), 
    			new Source(),
    			new Output()
    	);
		gyroControl.setSetpoint(0);
		this.targetDistance = targetDistance;
		requires(Robot.drivetrain);	
		
	}

	// Called just before this Command runs the first time
	protected void initialize() { 
		Robot.drivetrain.resetEncoders();
		Robot.drivetrain.resetGyro();
		System.out.println("Init");
		//startEncoderValue = Robot.drivetrain.getRightEncoderDistance();
		setSetpoint(targetDistance);
		this.getPIDController().setPID(
				SmartDashboard.getNumber("DriveDistanceEncodersPID P", 0),
				SmartDashboard.getNumber("DriveDistanceEncodersPID I", 0),
				SmartDashboard.getNumber("DriveDistanceEncodersPID D", 0)
				);

		gyroControl.enable();
		gyroControl.setPID(
				SmartDashboard.getNumber("RotateDegreesPID P", 0),
				SmartDashboard.getNumber("RotateDegreesPID I", 0),
				SmartDashboard.getNumber("RotateDegreesPID D", 0)
				);
		//targetDistance = SmartDashboard.getNumber("Test Distance", 170);
		this.getPIDController().setSetpoint(targetDistance);
		double rampSeconds = SmartDashboard.getNumber("RampSeconds", 2.5);
		Robot.drivetrain.setRamp(rampSeconds);
		this.getPIDController().setAbsoluteTolerance(DRIVE_DISTANCE_THRESHOLD);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//System.out.println("[DriveDistanceEncodersCommand] distance left:" + (targetDistance - Robot.drivetrain.getEncoderDistance()));
		//System.out.println("hey exec");
		SmartDashboard.putNumber("Right Distance", Robot.drivetrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Left Distance", Robot.drivetrain.getLeftEncoderDistance());
		//SmartDashboard.putNumber("Velocity", Robot.drivetrain.getEncoderVelocity());
		SmartDashboard.putNumber("Output", output);
		SmartDashboard.putNumber("Angle", Robot.drivetrain.getGyroAngle());
		SmartDashboard.putNumber("Angle Output", angleOutput);
		SmartDashboard.putNumber("StartEncoderValue", startEncoderValue);
		//System.out.println(angleOutput);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		//return (Robot.drivetrain.getRightEncoderDistance() > targetDistance && Math.abs(output) < PID_CLOSE_ENOUGH_THRESHOLD);
		if(Math.abs(Robot.drivetrain.getRightEncoderDistance() - targetDistance) <= 1 && !isSet) {
			timeFirstInRange = Timer.getFPGATimestamp();
			isSet = true;
		} else {
			isSet = false;
		}
		return this.getPIDController().onTarget() && Timer.getFPGATimestamp() - timeFirstInRange > 2;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.tankDrive(0, 0);
		gyroControl.disable();
		SmartDashboard.putNumber("Right Distance", Robot.drivetrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Left Distance", Robot.drivetrain.getLeftEncoderDistance());
		this.getPIDController().setPID(0, 0, 0);
		gyroControl.setPID(0, 0, 0);
		System.out.println("END");
		Robot.drivetrain.setRamp(0);
	}

	@Override
	protected double returnPIDInput() {
		//return Robot.drivetrain.getRightEncoderDistance() + startEncoderValue;
	    return Robot.drivetrain.getRightEncoderDistance();
	}
	protected double returnPIDInputGyro() {
		return Robot.drivetrain.getGyroAngle();
	}
	@Override
	protected void usePIDOutput(double output) {
		//if (!isFinished()) {
			Robot.drivetrain.tankDrive(output + DriveStraightWithRampingCommand.angleOutput, output - DriveStraightWithRampingCommand.angleOutput);
			this.output = output;
		//}
	}
	protected class Source implements PIDSource {
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			return returnPIDInputGyro();
		}		
	}
	protected class Output implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			DriveStraightWithRampingCommand.angleOutput = output;
		}		
	}
}

//Values: For a distance of 170, a P of 0.016, and a D of 0.03
//For a distance of 290, a P of 0.016 and a D of 0.03
//For turning, make it 0.04
