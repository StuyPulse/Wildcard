package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpatulaLeftDeacquireCommand extends Command {

    public SpatulaLeftDeacquireCommand() {
        requires(Robot.spatula);
    }

    @Override
    protected void execute() {
        Robot.spatula.leftSpatulaDeacquire();
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
