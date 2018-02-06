package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrabArmAndAcquirerAcquireCommand extends CommandGroup {

    public CrabArmAndAcquirerAcquireCommand() {
        addParallel(new CrabArmAcquireCommand());
        addSequential(new SpatulaAcquireCommand());
    }
}