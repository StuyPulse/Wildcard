package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CrabArrowAcquireUntilAcquiredCommand extends Command {

    public CrabArrowAcquireUntilAcquiredCommand() {
    }

    @Override
    protected void execute() {
        new CrabArrowAcquireCommand();
    }

    @Override
    protected boolean isFinished() {
        return Robot.spatula.isCubeDetected();
    }

    @Override
    protected void end() {
        new CrabArrowStopCommand();
    }
}
