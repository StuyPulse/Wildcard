package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainRotateAbsoluteDegreesControlSpeedPIDCommand extends DrivetrainRotateDegreesControlSpeedPIDCommand {

    public DrivetrainRotateAbsoluteDegreesControlSpeedPIDCommand(double targetAngle, double speedChange) {
        super(targetAngle, speedChange);
    }

    @Override
    protected double getAngle() {
        return Robot.drivetrain.getAbsoluteGyroAngle();

    }
}
