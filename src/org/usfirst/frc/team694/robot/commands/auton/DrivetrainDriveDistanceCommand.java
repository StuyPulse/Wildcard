package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class DrivetrainDriveDistanceCommand extends Command {

    protected double targetDistance;

    private double startDistance;

    public DrivetrainDriveDistanceCommand(double distance) {
        this.targetDistance = distance;
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startDistance = getRawDistance();
    }

    private double getRawDistance() {
        return Robot.drivetrain.getEncoderDistance();
    }

    protected double getDistance() {
        return getRawDistance() - startDistance;
    }

    protected double getDistanceFromTarget() {
        return targetDistance - getDistance();
    }
}
