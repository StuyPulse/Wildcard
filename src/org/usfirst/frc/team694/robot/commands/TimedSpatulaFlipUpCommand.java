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
        addSequential(new WaitCommand(RobotMap.PRE_FLIP_WAIT_TIME + 0.75));
        addSequential(new SpatulaFlipUpCommand());
        addSequential(new WaitCommand(RobotMap.POST_FLIP_WAIT_TIME));
    }
}
