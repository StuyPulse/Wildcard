package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TimedFlapAndFlipToggleCommand extends CommandGroup {

    public TimedFlapAndFlipToggleCommand() {
        addSequential(new WaitCommand(RobotMap.PRE_FLIP_WAIT_TIME));
        addSequential(new FlapAndFlipUpCommand());
        addSequential(new WaitCommand(RobotMap.POST_FLIP_WAIT_TIME));
    }
}
