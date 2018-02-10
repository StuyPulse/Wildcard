package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class CrabArmFlapAndSpatulaFlipUpCommand extends CommandGroup {
private static final int timeDelayForSpatulaFlip = 2;
    public CrabArmFlapAndSpatulaFlipUpCommand() {
        addSequential(new CrabArmFlapOutCommand());
        addSequential(new WaitCommand(timeDelayForSpatulaFlip));
        addSequential(new SpatulaFlipUpCommand());
        addSequential(new WaitCommand(timeDelayForSpatulaFlip));
        addSequential(new CrabArmStopCommand());
    }
}
