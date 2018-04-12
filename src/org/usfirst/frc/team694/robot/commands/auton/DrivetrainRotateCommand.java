package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class DrivetrainRotateCommand extends Command {

    protected double targetAngle;

    public DrivetrainRotateCommand(double targetAngle) {
        this.targetAngle = targetAngle;
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
//        Robot.drivetrain.resetGyro();
        Robot.drivetrain.lowGearShift();
    }

    @Override
    protected void end() {
        Robot.drivetrain.highGearShift();
    }

    protected double getDeltaAngle() {
        return Robot.drivetrain.getGyroAngle() - targetAngle;
    }

}
