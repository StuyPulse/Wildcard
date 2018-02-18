package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SpatulaStopCommand extends InstantCommand {

    public SpatulaStopCommand() {
        requires(Robot.spatula);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.spatula.stop();
    }
}
