package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public abstract class DrivetrainRotateDegreesPIDCommand extends DrivetrainRotateCommand {

    private double gyroPIDOutput;

    private boolean isSet = false;

    private double lastTimeNotOnTarget;

    private PIDController gyroPIDController;

    public DrivetrainRotateDegreesPIDCommand(double targetAngle) {
        super(targetAngle);

        gyroPIDController = new PIDController(0, 0, 0, new GyroPIDSource(), new GyroPIDOutput());

    }

    @Override
    protected void initialize() {
        super.initialize();
        lastTimeNotOnTarget = Timer.getFPGATimestamp();

		Robot.drivetrain.setRamp(SmartDashboard.getNumber("RotateDegreesPID RampSeconds", 0.03));

		gyroPIDController.setPID(
				SmartDashboard.getNumber("RotateDegreesPID P", 0.03), 
    			0, 
    			SmartDashboard.getNumber("RotateDegreesPID D", 0.06)
			);
        gyroPIDController.setSetpoint(targetAngle);
        gyroPIDController.enable();

		System.out.println("[RotatePID] START");
	}

    @Override
    protected void execute() {
        if (Math.abs(getAngle() - targetAngle) < 10 && !isSet) {
            isSet = true;
            gyroPIDController.reset();
            gyroPIDController.enable();
            gyroPIDController.setPID(
                    SmartDashboard.getNumber("RotateDegreesPID P", 0.0),
                    SmartDashboard.getNumber("RotateDegreesPID I", 0.0),
                    SmartDashboard.getNumber("RotateDegreesPID D", 0.0));
        }
        if (!onTarget()) {
            lastTimeNotOnTarget = Timer.getFPGATimestamp();
        }

        double output = gyroPIDOutput;
        if (Math.abs(output) < 0.1) {
            output = 0.1 * Math.signum(output);
        }
        Robot.drivetrain.tankDrive(output, -output);

    }

    @Override
    protected boolean isFinished() {
        return onTarget() && Timer.getFPGATimestamp() - lastTimeNotOnTarget > 0.5;
    }

    // Called once after isFinished returns true
    protected void end() {
        super.end();
        Robot.drivetrain.stop();
        Robot.drivetrain.setRamp(0);

        System.out.println("[RotatePID] END");
        System.out.println(getAngle());
    }

    private boolean onTarget() {
        return Math.abs(getAngle() - targetAngle) <= 2;
    }

    protected abstract double getAngle();

    private class GyroPIDSource implements PIDSource {
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {}

        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }

        @Override
        public double pidGet() {
            return getAngle();
        }
    }

    private class GyroPIDOutput implements PIDOutput {
        @Override
        public void pidWrite(double output) {
            gyroPIDOutput = output;
        }    
    }

}
//values for 90 degrees P:0.02645, I:0.004, D:0.06, but takes a while