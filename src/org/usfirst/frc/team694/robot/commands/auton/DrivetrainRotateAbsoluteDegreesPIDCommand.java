package org.usfirst.frc.team694.robot.commands.auton;

/**
 *
 */
public class DrivetrainRotateAbsoluteDegreesPIDCommand extends DrivetrainRotateDegreesPIDCommand {

    public DrivetrainRotateAbsoluteDegreesPIDCommand(double targetAngle) {
        super(targetAngle);
    }

    @Override
    protected double getAngle() {
        return 0; // TODO: Get absolute drivetrain angle
    }

}
