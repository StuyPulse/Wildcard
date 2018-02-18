package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainPiotrDriveCommand extends Command {

    private double rightTrigger;
    private double leftTrigger;
    
    public DrivetrainPiotrDriveCommand() {
        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        boolean arcadeDrive = false;
        boolean wasPressed = false;

        rightTrigger = Robot.oi.driverGamepad.getRawLeftTriggerAxis();
        leftTrigger = Robot.oi.driverGamepad.getRawRightTriggerAxis();

        double leftJoystickXValue = Robot.oi.driverGamepad.getLeftX();

        double leftJoystickYValue = Robot.oi.driverGamepad.getLeftY();
        double rightJoystickYValue = Robot.oi.driverGamepad.getRightY();

        if (Robot.oi.driverGamepad.getRawButton(1) && wasPressed == false) {
            arcadeDrive = !arcadeDrive;
        }
        wasPressed = Robot.oi.driverGamepad.getRawButton(1);
        if (arcadeDrive) {
            Robot.drivetrain.arcadeDrive((-1.0 * leftTrigger + rightTrigger), -1.0 * leftJoystickXValue);
        } else {
            Robot.drivetrain.tankDrive(-1.0 * rightJoystickYValue, -1.0 * leftJoystickYValue);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
