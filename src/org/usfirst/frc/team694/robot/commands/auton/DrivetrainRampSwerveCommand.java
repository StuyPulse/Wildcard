package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

/**
 * Runs ramping, but changes the angle at a certain point
 */
public class DrivetrainRampSwerveCommand extends DriveStraightWithRampingCommand {
    
    private double distanceTillSwerve;
    private double angleToSwerve;

    private boolean isSet;

    public DrivetrainRampSwerveCommand(double distance, double distanceTillSwerve, double angleToSwerve) {
        super(distance);
        this.distanceTillSwerve = distanceTillSwerve;
        this.angleToSwerve = angleToSwerve;

        isSet = false;
    }

    @Override
    protected void initialize() {
        super.initialize();
        System.out.println("[DriveRampSwerve] init");
        Robot.drivetrain.resetEncoders();
        isSet = false;
    }

    @Override
    protected void execute() {
        super.execute();
        if (!isSet && Robot.drivetrain.getEncoderDistance() > distanceTillSwerve) {
            System.out.println("[DrivetrainRampSwerve] START TURNING " + Robot.drivetrain.getEncoderDistance()  );
            isSet = true;
            gyroControl.setSetpoint(angleToSwerve);
        }
    }
}
