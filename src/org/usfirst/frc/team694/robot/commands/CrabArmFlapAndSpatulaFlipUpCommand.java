package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrabArmFlapAndSpatulaFlipUpCommand extends CommandGroup {

    public CrabArmFlapAndSpatulaFlipUpCommand() {
        addSequential(new CrabArmFlapOutCommand());
        addSequential(new SpatulaFlipUpCommand());
    }
}
