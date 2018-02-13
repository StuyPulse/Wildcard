package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TimedToggleCrabArmFlapOutAndSpatulaFlipCommand extends CommandGroup {

    public TimedToggleCrabArmFlapOutAndSpatulaFlipCommand() {
        addSequential(new WaitCommand(RobotMap.PRE_FLIP_WAIT_TIME));
        addSequential(new SpatulaFlipToggleCommand());
        addSequential(new WaitCommand(RobotMap.POST_FLIP_WAIT_TIME));
    }
}
