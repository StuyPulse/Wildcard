package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class QuisitorFlipToggleCommand extends InstantCommand {

    public QuisitorFlipToggleCommand() {
        requires(Robot.quisitor);
    }

    protected void initialize() {
        Robot.quisitor.flipToggle();
    }
}
