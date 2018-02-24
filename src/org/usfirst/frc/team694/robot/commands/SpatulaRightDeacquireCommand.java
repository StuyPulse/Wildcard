package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpatulaRightDeacquireCommand extends Command {

    public SpatulaRightDeacquireCommand() {
        requires(Robot.spatula);
    }

    @Override
    protected void execute() {
        Robot.spatula.rightSpatulaDeacquire();
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
