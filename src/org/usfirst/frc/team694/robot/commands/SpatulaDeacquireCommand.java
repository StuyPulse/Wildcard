package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpatulaDeacquireCommand extends Command {

    public SpatulaDeacquireCommand() {
        requires(Robot.spatula);
    }

    @Override
    protected void execute() {
        Robot.spatula.deacquire();
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
