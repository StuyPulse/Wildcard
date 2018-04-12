package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LineSensorRecalibrationCommand extends Command {
    private double offset;

    public LineSensorRecalibrationCommand(double offset) {
        this.offset = offset;
    }

    @Override
    protected boolean isFinished() {
        return Robot.drivetrain.isOnLine();
    }

    @Override
    protected void end() {
        Robot.drivetrain.setEncoders(offset);
    }

}
