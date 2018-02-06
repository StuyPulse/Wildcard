package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpatulaAcquireUntilAcquiredCommand extends Command {


    public SpatulaAcquireUntilAcquiredCommand() {
        requires(Robot.spatula);
    }
    protected void execute() {
        Robot.spatula.acquire();
    }

    protected boolean isFinished() {
        return Robot.spatula.isCubeDetected();
    }

    protected void end() {
        Robot.spatula.stop();
    }
}
