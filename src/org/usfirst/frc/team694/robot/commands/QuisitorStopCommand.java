package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class QuisitorStopCommand extends InstantCommand {

    public QuisitorStopCommand() {
        requires(Robot.quisitor);
    }

    @Override
    protected void initialize() {
        Robot.quisitor.stop();
    }
}
