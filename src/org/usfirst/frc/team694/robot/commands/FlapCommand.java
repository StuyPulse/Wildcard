package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FlapCommand extends CommandGroup {

    public FlapCommand() {
        addParallel(new CrabArmFlapOutCommand(), 1.5);
    }
}