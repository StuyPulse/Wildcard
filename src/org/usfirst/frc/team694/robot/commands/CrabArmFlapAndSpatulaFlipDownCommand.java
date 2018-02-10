package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CrabArmFlapAndSpatulaFlipDownCommand extends CommandGroup {
    private static final int timeDelayForSpatulaFlip = 2;
    public CrabArmFlapAndSpatulaFlipDownCommand() {
        addSequential(new CrabArmFlapOutCommand());
        addSequential(new WaitCommand(timeDelayForSpatulaFlip));
        addSequential(new SpatulaFlipDownCommand());
        addSequential(new WaitCommand(timeDelayForSpatulaFlip));
        addSequential(new CrabArmStopCommand());
    }
}
