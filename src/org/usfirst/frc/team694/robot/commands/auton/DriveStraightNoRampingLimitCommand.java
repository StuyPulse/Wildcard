package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

public class DriveStraightNoRampingLimitCommand extends DriveStraightWithRampingCommand {
    public DriveStraightNoRampingLimitCommand(double targetDistance) {
        super(targetDistance);
    }

    @Override
    protected void initialize() {
        super.initialize();
        Robot.drivetrain.setRamp(0);
    }
}
