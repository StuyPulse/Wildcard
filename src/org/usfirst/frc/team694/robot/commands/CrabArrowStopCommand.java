package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrabArrowStopCommand extends CommandGroup {

    public CrabArrowStopCommand() {
        addParallel(new CrabArmStopCommand());
        addSequential(new QuisitorStopCommand());
    }
}
