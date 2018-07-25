package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class QuisitorDeacquireCommand extends Command {

    public QuisitorDeacquireCommand() {
        requires(Robot.quisitor);
    }

    @Override
    protected void execute() {
        Robot.quisitor.deacquire();
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
