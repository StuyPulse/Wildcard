package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

/**
 *
 */
public class DrivetrainRotateAbsoluteDegreesPIDCommand extends DrivetrainRotateDegreesPIDCommand {

    public DrivetrainRotateAbsoluteDegreesPIDCommand(double targetAngle) {
        super(targetAngle);
    }

    @Override
    protected double getAngle() {
        return Robot.drivetrain.getAbsoluteGyroAngle();
    }

}
