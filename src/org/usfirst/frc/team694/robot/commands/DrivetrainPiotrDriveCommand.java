package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainPiotrDriveCommand extends Command {

    public DrivetrainPiotrDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        boolean arcadeDrive = false;
        boolean wasPressed = false;

        double rightTrigger = Robot.oi.driverGamepad.getRawLeftTriggerAxis();
        double leftTrigger = Robot.oi.driverGamepad.getRawRightTriggerAxis();
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

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }

}
