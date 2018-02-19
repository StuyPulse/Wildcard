package org.usfirst.frc.team694.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Runs ramping, but changes the angle at a certain point
 */
@Deprecated
public class DrivetrainRampSwerveCommand extends CommandGroup {

    public DrivetrainRampSwerveCommand() {
        //DriveStraightWithRampingCommand command = new DriveStraightWithRampingCommand(targetDistance)
    }
/*    private double distanceTillSwerve;
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
    */
}
