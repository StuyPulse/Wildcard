package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

/**
 *
 */
public class CrabArrowAcquireUntilAcquiredCommand extends CrabArrowAcquireCommand {

    @Override
    protected boolean isFinished() {
        return Robot.spatula.isCubeDetected();
    }

}
