package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.util.Gamepad;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GamepadRumbleWhenCubeDetectedCommand extends Command {

    private boolean wasCubeDetected; // Was the cube detected last frame?
    private Command rumbleCommand; 

    public GamepadRumbleWhenCubeDetectedCommand(Gamepad gamepad, double intensity, double duration) {
        rumbleCommand = new GamepadRumbleCommand(gamepad, 0.5, 0.3);
    }

    @Override
    protected void initialize() {
        wasCubeDetected = false; 
    }

    @Override
    protected void execute() {
        if(Robot.quisitor.isCubeDetected() && !wasCubeDetected) {
            rumbleCommand.start();
        }
        wasCubeDetected = Robot.quisitor.isCubeDetected(); 
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
