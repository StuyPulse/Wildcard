package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SpatulaFlipToggleCommand extends InstantCommand {

    public SpatulaFlipToggleCommand() {
        super();
        requires(Robot.spatula);
        
    }

    @Override
    protected void initialize() {
        Robot.spatula.toggle();
    }
}
