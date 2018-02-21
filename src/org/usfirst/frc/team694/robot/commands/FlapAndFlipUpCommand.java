package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class FlapAndFlipUpCommand extends CommandGroup {

    public FlapAndFlipUpCommand() {
        addParallel(new CrabArmFlapOutCommand(), RobotMap.PRE_FLIP_WAIT_TIME + RobotMap.POST_FLIP_WAIT_TIME + 0.75);
        addSequential(new TimedSpatulaFlipUpCommand());
    }
}
