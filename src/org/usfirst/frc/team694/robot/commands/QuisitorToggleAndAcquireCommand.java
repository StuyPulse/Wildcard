package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class QuisitorToggleAndAcquireCommand extends CommandGroup {

    public QuisitorToggleAndAcquireCommand() {
        addSequential(new QuisitorFlipToggleCommand());
        addSequential(new QuisitorMoveSpeedCommand(0.25), 1);
    }
}
