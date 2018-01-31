package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.util.IRSensor;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This will flip the cube when the IR sensor detects that the cube is not
 * upright, and leave it when it detects the cube is upright
 */
public class AcquirerDetectFlipCommand extends Command {

    public AcquirerDetectFlipCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.acquirer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (IRSensor.isCubeDetected()) {
            Robot.acquirer.flipUp();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
