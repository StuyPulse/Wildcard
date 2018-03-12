package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class QuisitorLeftDeacquireCommand extends Command {

    public QuisitorLeftDeacquireCommand() {
        requires(Robot.quisitor);
    }

    @Override
    protected void execute() {
        Robot.quisitor.leftQuisitorDeacquire();
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
