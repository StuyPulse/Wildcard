package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BITCOINAutoCommand extends CommandGroup {

    public BITCOINAutoCommand() {
        addSequential(new CrabArrowAcquireUntilAcquiredCommand());
        addSequential(new BITCOINManualCommand());
    }
}
