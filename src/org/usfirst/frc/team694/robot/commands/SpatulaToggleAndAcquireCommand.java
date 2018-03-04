package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SpatulaToggleAndAcquireCommand extends CommandGroup {

    public SpatulaToggleAndAcquireCommand() {
        addParallel(new SpatulaMoveSpeedCommand(0.25), 1);
        addSequential(new SpatulaFlipToggleCommand());
    }
}
