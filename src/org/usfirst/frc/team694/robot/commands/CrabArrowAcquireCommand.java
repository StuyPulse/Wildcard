package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrabArrowAcquireCommand extends CommandGroup {
    //CrabArrow = CrabArms and Spatula

    public CrabArrowAcquireCommand() {
        addSequential(new SpatulaFlipDownIfUpCommand());
        addParallel(new LiftMoveToHeightCommand(0));
        addParallel(new CrabArmAcquireCommand());
        addSequential(new SpatulaAcquireCommand());  
    }
}