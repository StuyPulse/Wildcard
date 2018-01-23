package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainPiotrDriveCommand extends Command {

    private boolean arcadeDrive;
    private boolean wasPressed;

    private double rightTrigger;
    private double leftTrigger;

    private double leftTriggerSquared;
    private double rightTriggerSquared;
    
    private double leftJoystickXValue;
    
    private double leftJoystickYValue;
    private double rightJoystickYValue;

    public DrivetrainPiotrDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        rightTriggerSquared = ((rightTrigger + 1) / 2) * ((rightTrigger + 1) / 2);
        leftTriggerSquared = ((leftTrigger + 1) / 2) * ((leftTrigger + 1) / 2);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        rightTrigger = Robot.oi.driverGamepad.getRawLeftTriggerAxis();
        leftTrigger = Robot.oi.driverGamepad.getRawRightTriggerAxis();
        
        leftJoystickXValue = Robot.oi.driverGamepad.getLeftX() * Robot.oi.driverGamepad.getLeftX() * Math.signum(Robot.oi.driverGamepad.getLeftX());
        
        leftJoystickYValue = Math.signum(Robot.oi.driverGamepad.getLeftY()) * Robot.oi.driverGamepad.getLeftY() * Robot.oi.driverGamepad.getLeftY();
        rightJoystickYValue = Math.signum(Robot.oi.driverGamepad.getRightY()) * Robot.oi.driverGamepad.getRightY()* Robot.oi.driverGamepad.getRightY();
        
        rightTriggerSquared = ((rightTrigger + 1) / 2) * ((rightTrigger + 1) / 2);
        leftTriggerSquared = ((leftTrigger + 1) / 2) * ((leftTrigger + 1) / 2);

        if (Robot.oi.driverGamepad.getRawButton(1) && wasPressed == false) {
            arcadeDrive =!arcadeDrive;
        }
        wasPressed = Robot.oi.driverGamepad.getRawButton(1);
        if (arcadeDrive) {
            Robot.drivetrain.arcadeDrive((-1.0 * leftTriggerSquared) + rightTriggerSquared, -1.0 * leftJoystickXValue);
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
