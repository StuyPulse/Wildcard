package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrabArrowAcquireCommand extends CommandGroup {
    //CrabArrow = CrabArms and Spatula

    public CrabArrowAcquireCommand() {
        // Removed by order of Sir Blay:
//        addSequential(new SpatulaFlipDownIfUpCommand());
        addParallel(new CrabArmAcquireCommand());
        addSequential(new SpatulaAcquireCommand());  
    }
}