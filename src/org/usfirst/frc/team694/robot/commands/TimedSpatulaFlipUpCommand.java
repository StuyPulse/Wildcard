package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TimedSpatulaFlipUpCommand extends CommandGroup {

    public TimedSpatulaFlipUpCommand() {
        addSequential(new PrintCommand("[TimedSpatulaFlipUpCommand] WAITING"));
        addSequential(new WaitCommand(RobotMap.PRE_FLIP_WAIT_TIME));
        addSequential(new PrintCommand("[TimedSpatulaFlipUpCommand] FIRING"));
        addSequential(new SpatulaFlipUpCommand());
        addSequential(new WaitCommand(RobotMap.POST_FLIP_WAIT_TIME));
        addSequential(new PrintCommand("[TimedSpatulaFlipUpCommand] DONE"));
    }
}
