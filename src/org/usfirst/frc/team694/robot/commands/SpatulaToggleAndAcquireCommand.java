package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SpatulaToggleAndAcquireCommand extends CommandGroup {

    public SpatulaToggleAndAcquireCommand() {
        addSequential(new SpatulaFlipToggleCommand());
        addSequential(new SpatulaMoveSpeedCommand(0.25), 1);
    }
}
