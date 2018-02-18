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
    
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.spatula.acquireSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.spatula.stop();
    }
}
