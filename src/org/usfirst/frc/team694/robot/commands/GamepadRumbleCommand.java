package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.util.Gamepad;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GamepadRumbleCommand extends Command {
    private Gamepad gamepad; 
    private double intensity; 
    private double duration; 
    private double startTime; 

    public GamepadRumbleCommand(Gamepad gamepad, double intensity, double duration) {
        this.gamepad = gamepad; 
        this.intensity = intensity; 
        this.duration = duration; 
    }

    @Override
    protected void initialize() {
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    protected void execute() {
        gamepad.gamepadRumble(intensity);
    }

    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime >= duration;
    }

    @Override
    protected void end() {
        gamepad.gamepadRumble(0);
    }

    @Override
    protected void interrupted() {
        gamepad.gamepadRumble(0);
    }
}
