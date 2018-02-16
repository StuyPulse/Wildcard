package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpatulaMoveSpeedCommand extends Command {

    private double speed;
    
    // TESTING ONLY
    public SpatulaMoveSpeedCommand(double speed) {
        this.speed = speed;
        requires(Robot.spatula);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        Robot.spatula.acquireSpeed(speed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.spatula.stop();
    }
}
