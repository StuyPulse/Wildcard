package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

public class DriveStraightRampUpOnlyCommand extends DriveStraightWithRampingCommand {

    public DriveStraightRampUpOnlyCommand(double targetDistance) {
        super(targetDistance);
    }

    @Override
    protected void initialize() {
        super.initialize();
        // 100% bang bang controller
        this.getPIDController().setPID(1,0,0);
    }

    @Override
    protected boolean isFinished() {
        return Robot.drivetrain.getEncoderDistance() > targetDistance;
    }

}
