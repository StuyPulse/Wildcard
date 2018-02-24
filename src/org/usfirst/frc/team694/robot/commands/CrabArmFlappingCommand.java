package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CrabArmFlappingCommand extends Command {
    boolean motorOn;
    double flapTimer;
    double startTime;
    int seconds;
    
    public CrabArmFlappingCommand() {
        requires(Robot.crabArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    seconds = 0; //Set this to the amount of time it takes for the arms to return to IN, determine in testing
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.crabArm.flapPush();
        startTime = Timer.getFPGATimestamp();
        if (Timer.getFPGATimestamp() - startTime > seconds) {
            Robot.crabArm.flapCoast();
            startTime = Timer.getFPGATimestamp();
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
