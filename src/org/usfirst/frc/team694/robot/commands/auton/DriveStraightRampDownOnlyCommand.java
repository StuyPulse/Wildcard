package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

public class DriveStraightRampDownOnlyCommand extends DriveStraightWithRampingCommand {
    public DriveStraightRampDownOnlyCommand(double targetDistance) {
        super(targetDistance);
    }

    @Override
    protected void initialize() {
        super.initialize();
        Robot.drivetrain.setRamp(0);
    }
}
