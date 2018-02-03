package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftMoveToHeightCommand extends Command {
    private double height;
    private double startHeight;

    public LiftMoveToHeightCommand(double height) {
        this.height = height;
        requires(Robot.lift);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startHeight = Robot.lift.getLiftHeight();
        Robot.lift.resetEncoders();
        Robot.lift.setBrakeOff();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (startHeight > height) {
            Robot.lift.goDown();
        } else if (startHeight < height) {
            Robot.lift.goUp();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.lift.getLiftHeight() == height);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
