package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivetrainRotateDegreesPIDCommand extends PIDCommand {
    public double targetAngle;
    private boolean isSet = false;

    private double lastTimeNotOnTarget;

    public DrivetrainRotateDegreesPIDCommand(double targetAngle) {
        super(0.008, 0, 0);
        this.targetAngle = targetAngle;
        setSetpoint(targetAngle);
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	    System.out.println("[RotatePID] START");
		Robot.drivetrain.resetGyro();
		Robot.drivetrain.lowGearShift();
		Robot.drivetrain.setRamp(SmartDashboard.getNumber("RotateDegreesPID RampSeconds", 0.03));
		lastTimeNotOnTarget = Timer.getFPGATimestamp();
		this.getPIDController().setPID(
				SmartDashboard.getNumber("RotateDegreesPID P", 0.03), 
    			0, 
    			SmartDashboard.getNumber("RotateDegreesPID D", 0.06)
				);
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Math.abs(Robot.drivetrain.getGyroAngle() - targetAngle) < 10 && !isSet) {
            isSet = true;
            this.getPIDController().reset();
            this.getPIDController().enable();
            this.getPIDController().setPID(SmartDashboard.getNumber("RotateDegreesPID P", 0.03),
                    SmartDashboard.getNumber("RotateDegreesPID I", 0.001),
                    SmartDashboard.getNumber("RotateDegreesPID D", 0.06));
        }
        if (!onTarget()) {
            lastTimeNotOnTarget = Timer.getFPGATimestamp();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return onTarget() && Timer.getFPGATimestamp() - lastTimeNotOnTarget > 0.5;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.tankDrive(0, 0);
        Robot.drivetrain.highGearShift();
        Robot.drivetrain.setRamp(0);
        System.out.println("[RotatePID] END");
//        Timer.delay(1);
        System.out.println(Robot.drivetrain.getGyroAngle());
    }

    @Override
    protected double returnPIDInput() {
        // TODO Auto-generated method stub
        return Robot.drivetrain.getGyroAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        if (Math.abs(output) < 0.15) {
            output = 0.15 * Math.signum(output);
        }
        System.out.println("out: " + output);
        Robot.drivetrain.tankDrive(output, -output);
    }

    private boolean onTarget() {
        return Math.abs(Robot.drivetrain.getGyroAngle() - targetAngle) <= 2;
    }

}
//values for 90 degrees P:0.02645, I:0.004, D:0.06, but takes a while