package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.util.Gamepad;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GamepadRumbleCommand extends Command {
    Gamepad gamepad; 
    double intensity; 
    double duration; 
    double startTime; 
    
    public GamepadRumbleCommand(Gamepad gamepad, double intensity, double duration) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.gamepad = gamepad; 
        this.intensity = intensity; 
        this.duration = duration; 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        gamepad.gamepadRumble(intensity);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime >= duration;
    }

    // Called once after isFinished returns true
    protected void end() {
        gamepad.gamepadRumble(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        gamepad.gamepadRumble(0);
    }
}
