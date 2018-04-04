package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightWithRampingCommand extends DriveStraightPIDCommand {

    private static final double DRIVE_DISTANCE_THRESHOLD = 1;

    protected boolean isSet = false;
    protected double timeFirstInRange;

    protected PIDController speedPIDController;

    // Multiplies our speed, limiting it
    private double speedScaleFactor;

    private double speedPIDOutput;

    public DriveStraightWithRampingCommand(double targetDistance) {
        super(targetDistance, 1);
    }

    @Override
    protected void initialize() {
        super.initialize();
        // Initialized the controller here to prevent "java.lang.OutOfMemoryError: unable to create new native thread" error
        speedPIDController = new PIDController(0,0,0, new SpeedPIDSource(), new SpeedPIDOutput());

        speedScaleFactor = 1;

        Robot.drivetrain.setRamp(SmartDashboard.getNumber("DriveStraight RampSeconds", 2.5));

        //startEncoderValue = Robot.drivetrain.getRightEncoderDistance();
        speedPIDController.setSetpoint(targetDistance);
        speedPIDController.setPID(
                SmartDashboard.getNumber("DriveDistanceEncodersPID P", 0),
                SmartDashboard.getNumber("DriveDistanceEncodersPID I", 0),
                SmartDashboard.getNumber("DriveDistanceEncodersPID D", 0));
        speedPIDController.setAbsoluteTolerance(DRIVE_DISTANCE_THRESHOLD);

        speedPIDController.enable();

        System.out.println("[DriveStraight] Init");
    }

    @Override
    protected void execute() {
        System.out.println("[DriveStraightWithRamping] ===============================");
//        SmartDashboard.putNumber("DriveStraight Encoder Vel", Robot.drivetrain.getSpeed());

        double output = speedPIDOutput;
        if (Math.abs(output) < 0.15) {
            if (isOnTarget())
                output = 0;
            else
                output = 0.15 * Math.signum(output);
        }
        output = Math.min(Math.max(-1, output), 1);

        double left = output * speedScaleFactor  + getGyroPIDOutput();
        double right = output * speedScaleFactor - getGyroPIDOutput();
        Robot.drivetrain.tankDrive(left, right);
//        System.out.println("[Drive Straight Ramp] Angle output: " + getGyroPIDOutput());
    }

    @Override
    protected boolean isFinished() {

        boolean inRange = isOnTarget();
        //return (Robot.drivetrain.getRightEncoderDistance() > targetDistance && Math.abs(output) < PID_CLOSE_ENOUGH_THRESHOLD);
        if (inRange && !isSet) {
            System.out.println("[DriveStraight] SET! " + Robot.drivetrain.getEncoderDistance());
            timeFirstInRange = Timer.getFPGATimestamp();
            isSet = true;
        } else if (!inRange) {
            isSet = false;
        }
        return inRange && Timer.getFPGATimestamp() - timeFirstInRange > 0.5;
    }

    @Override
    protected void end() {
        super.end();
        Robot.drivetrain.stop();
        Robot.drivetrain.setRamp(0);

        speedPIDController.setPID(0, 0, 0);
        speedPIDController.disable();

        SmartDashboard.putNumber("DriveStraight FINAL Right Distance", Robot.drivetrain.getRightEncoderDistance());
        SmartDashboard.putNumber("DriveStraight FINAL Left Distance", Robot.drivetrain.getLeftEncoderDistance());
        System.out.println("[DriveStraight] END: " + Robot.drivetrain.getEncoderDistance());
    }

    @Override
    protected void interrupted() {
        end();
        System.out.println("[DriveStraight] INTERRUPTED");
    }

    private boolean isOnTarget() {
        return Math.abs(getDistanceFromTarget()) <= 3;
    }

    private class SpeedPIDSource implements PIDSource {
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {}

        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }

        @Override
        public double pidGet() {
            return getDistance();
        }
    }

    private class SpeedPIDOutput implements PIDOutput {
        @Override
        public void pidWrite(double output) {
            System.out.println("[DriveStraightWithRamping] speed output: " + output);
            speedPIDOutput = output;
        }    
    }
}

// P of 0.006, and a D of 0.03
//For turning, make it 0.04
