package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightWithRampingCommand extends PIDCommand {

    private static final double DRIVE_DISTANCE_THRESHOLD = 1;

    protected double targetDistance;
    protected double startEncoderValue;
    protected static double angleOutput;
    protected static boolean isSet = false;
    protected static double timeFirstInRange;

    // Multiplies our speed, limiting it
    private double speedScaleFactor;

    PIDController gyroControl;

    public DriveStraightWithRampingCommand(double targetDistance) {
        super(0, 0, 0);
        gyroControl = new PIDController(0, 0, 0, new GyroSource(), new GyroOutput());
        gyroControl.setSetpoint(0);
        this.targetDistance = targetDistance;
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
        speedScaleFactor = 1;

        Robot.drivetrain.resetEncoders();
        Robot.drivetrain.resetGyro();
        Robot.drivetrain.setRamp(SmartDashboard.getNumber("DriveStraight RampSeconds", 2.5));

        //startEncoderValue = Robot.drivetrain.getRightEncoderDistance();
        this.setSetpoint(targetDistance);
        this.getPIDController().setPID(
                SmartDashboard.getNumber("DriveDistanceEncodersPID P", 0),
                SmartDashboard.getNumber("DriveDistanceEncodersPID I", 0),
                SmartDashboard.getNumber("DriveDistanceEncodersPID D", 0));
        this.getPIDController().setSetpoint(targetDistance);
        this.getPIDController().setAbsoluteTolerance(DRIVE_DISTANCE_THRESHOLD);

        gyroControl.enable();
        gyroControl.setPID(
                SmartDashboard.getNumber("DriveStraightGyroPID P", 0),
                SmartDashboard.getNumber("DriveStraightGyroPID I", 0),
                SmartDashboard.getNumber("DriveStraightGyroPID D", 0));
        gyroControl.setSetpoint(0);

        System.out.println("[DriveStraight] Init");
    }

    @Override
    protected void execute() {
        //System.out.println("[DriveDistanceEncodersCommand] distance left:" + (targetDistance - Robot.drivetrain.getEncoderDistance()));
        SmartDashboard.putNumber("DriveStraight Encoder Vel", Robot.drivetrain.getSpeed());
        //System.out.println(angleOutput);
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
        Robot.drivetrain.stop();
        Robot.drivetrain.setRamp(0);

        this.getPIDController().setPID(0, 0, 0);

        gyroControl.disable();
        gyroControl.setPID(0, 0, 0);

        SmartDashboard.putNumber("DriveStraight FINAL Right Distance", Robot.drivetrain.getRightEncoderDistance());
        SmartDashboard.putNumber("DriveStraight FINAL Left Distance", Robot.drivetrain.getLeftEncoderDistance());
        System.out.println("[DriveStraight] END: " + Robot.drivetrain.getEncoderDistance());
    }

    @Override
    protected void interrupted() {
        end();
        System.out.println("[DriveStraight] INTERRUPTED");
    }

    @Override
    protected double returnPIDInput() {
        //return Robot.drivetrain.getRightEncoderDistance() + startEncoderValue;
        return Robot.drivetrain.getEncoderDistance();
    }

    @Override
    protected void usePIDOutput(double output) {
        if (Math.abs(output) < 0.2) {
            if (isOnTarget())
                output = 0;
            else
                output = 0.2 * Math.signum(output);
        }

        output = Math.min(Math.max(-1, output), 1);

        double left = output * speedScaleFactor  + DriveStraightWithRampingCommand.angleOutput;
        double right = output * speedScaleFactor - DriveStraightWithRampingCommand.angleOutput;

        Robot.drivetrain.tankDrive(left,right);

//        this.output = output;
    }

    private boolean isOnTarget() {
        return Math.abs(Robot.drivetrain.getEncoderDistance() - targetDistance) <= 3;
    }

    public void setTargetAngle(double angle) {
        gyroControl.setSetpoint(angle);
    }

    public void setSpeedScale(double speedScaleFactor) {
        this.speedScaleFactor = speedScaleFactor;
    }

    private class GyroSource implements PIDSource {
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
        }

        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }

        @Override
        public double pidGet() {
            return Robot.drivetrain.getGyroAngle();
        }
    }

    private class GyroOutput implements PIDOutput {
        @Override
        public void pidWrite(double output) {
            DriveStraightWithRampingCommand.angleOutput = output;
        }
    }
}

// P of 0.006, and a D of 0.03
//For turning, make it 0.04
