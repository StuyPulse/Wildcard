package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorGamepadCubeDetectedRumbleCommand extends Command {

    private double seconds;
    private double startTime;
    private double intensity;
    
    public OperatorGamepadCubeDetectedRumbleCommand(double seconds, double intensity) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        
        seconds = this.seconds;
        intensity = this.intensity;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.oi.operatorGamepad.gamepadRumble(intensity);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime == seconds;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
