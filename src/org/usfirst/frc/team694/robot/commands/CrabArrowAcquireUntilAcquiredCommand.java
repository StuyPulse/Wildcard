package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CrabArrowAcquireUntilAcquiredCommand extends Command {

    public CrabArrowAcquireUntilAcquiredCommand() {
    }

    protected void execute() {
        new CrabArrowAcquireCommand();
    }

    protected boolean isFinished() {
        return Robot.spatula.isCubeDetected();
    }

    protected void end() {
        new CrabArrowStopCommand();
    }
}
