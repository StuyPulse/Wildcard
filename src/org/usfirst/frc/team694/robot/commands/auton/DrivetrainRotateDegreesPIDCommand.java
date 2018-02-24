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
        super(0, 0, 0);
        this.targetAngle = targetAngle;

        this.setSetpoint(targetAngle);
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        lastTimeNotOnTarget = Timer.getFPGATimestamp();

        Robot.drivetrain.resetGyro();
		Robot.drivetrain.lowGearShift();
		Robot.drivetrain.setRamp(SmartDashboard.getNumber("RotateDegreesPID RampSeconds", 0.03));

		this.getPIDController().setPID(
				SmartDashboard.getNumber("RotateDegreesPID P", 0.03), 
    			0, 
    			SmartDashboard.getNumber("RotateDegreesPID D", 0.06)
			);

		System.out.println("[RotatePID] START");
	}

    @Override
    protected void execute() {
        if (Math.abs(Robot.drivetrain.getGyroAngle() - targetAngle) < 10 && !isSet) {
            isSet = true;
            this.getPIDController().reset();
            this.getPIDController().enable();
            this.getPIDController().setPID(SmartDashboard.getNumber("RotateDegreesPID P", 0.0),
                    SmartDashboard.getNumber("RotateDegreesPID I", 0.0),
                    SmartDashboard.getNumber("RotateDegreesPID D", 0.0));
        }
        if (!onTarget()) {
            lastTimeNotOnTarget = Timer.getFPGATimestamp();
        }
    }

    @Override
    protected boolean isFinished() {
        return onTarget() && Timer.getFPGATimestamp() - lastTimeNotOnTarget > 0.5;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.stop();
        Robot.drivetrain.highGearShift();
        Robot.drivetrain.setRamp(0);

        System.out.println("[RotatePID] END");
        System.out.println(Robot.drivetrain.getGyroAngle());
    }


    @Override
    protected double returnPIDInput() {
        return Robot.drivetrain.getGyroAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        // Minimum magnitude of turning
        if (Math.abs(output) < 0.1) {
            output = 0.1 * Math.signum(output);
        }
        Robot.drivetrain.tankDrive(output, -output);

        System.out.println("out: " + output);
    }

    private boolean onTarget() {
        return Math.abs(Robot.drivetrain.getGyroAngle() - targetAngle) <= 2;
    }

}
//values for 90 degrees P:0.02645, I:0.004, D:0.06, but takes a while