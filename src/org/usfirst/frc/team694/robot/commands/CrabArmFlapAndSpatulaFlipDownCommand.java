package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrabArmFlapAndSpatulaFlipDownCommand extends CommandGroup {

    public CrabArmFlapAndSpatulaFlipDownCommand() {
        addSequential(new CrabArmFlapOutCommand());
        addSequential(new SpatulaFlipDownCommand());
    }
}
