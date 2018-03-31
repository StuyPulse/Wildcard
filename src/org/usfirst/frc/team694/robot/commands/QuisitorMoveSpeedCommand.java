package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class QuisitorMoveSpeedCommand extends Command {

    private double speed;

    public QuisitorMoveSpeedCommand(double speed) {
        this.speed = speed;
        requires(Robot.quisitor);
    }

    @Override
    protected void execute() {
        Robot.quisitor.acquireSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.quisitor.stop();
    }
}
