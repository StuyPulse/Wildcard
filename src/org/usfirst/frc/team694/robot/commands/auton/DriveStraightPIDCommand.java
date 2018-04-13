package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightPIDCommand extends DrivetrainMoveInchesEncoderCommand {

    protected double speedScaleFactor = 1;

    private PIDController rotationPIDController;
    private double gyroPIDOutput;

    public DriveStraightPIDCommand(double targetDistance, double speed) {
        super(targetDistance, speed);
    }

    @Override
    protected void initialize() {
        super.initialize();
        rotationPIDController = new PIDController(0, 0, 0, new GyroPIDSource(), new GyroPIDOutput());
//        Robot.drivetrain.resetGyro();
        rotationPIDController.setSetpoint(Robot.drivetrain.getGyroAngle());
//        rotationPIDController.setSetpoint(0);
        rotationPIDController.setPID(
                SmartDashboard.getNumber("DriveStraightGyroPID P", 0),
                SmartDashboard.getNumber("DriveStraightGyroPID I", 0), 
                SmartDashboard.getNumber("DriveStraightGyroPID D", 0));
        rotationPIDController.enable();
    }

    @Override
    protected void execute() {
        Robot.drivetrain.tankDrive(moveSpeed*speedScaleFactor + getGyroPIDOutput(), moveSpeed*speedScaleFactor - getGyroPIDOutput());
        System.out.println("[DriveStraightPID] target: " + targetDistance + ", dist: " + getDistance());
    }

    @Override
    protected boolean isFinished() {
        return super.isFinished();
    }

    @Override
    protected void end() {
        super.end();
        rotationPIDController.setPID(0, 0, 0);
        rotationPIDController.disable();
    }

    protected double getGyroPIDOutput() {
        return gyroPIDOutput;
    }

    public void setTargetAngle(double angle) {
        rotationPIDController.setSetpoint(angle);
    }

    public void setSpeedScale(double speedScaleFactor) {
        this.speedScaleFactor = speedScaleFactor;
    }

    private class GyroPIDSource implements PIDSource {
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

    private class GyroPIDOutput implements PIDOutput {
        @Override
        public void pidWrite(double output) {
            System.out.println("[GyroPIDOutput] : " + output);
            gyroPIDOutput = output;
        }
    }
}
