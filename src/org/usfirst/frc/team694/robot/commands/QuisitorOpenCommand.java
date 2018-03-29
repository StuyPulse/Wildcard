package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class QuisitorOpenCommand extends InstantCommand {

    public QuisitorOpenCommand() {
        requires(Robot.quisitor);
    }

    @Override
    protected void initialize() {
        Robot.quisitor.open();
    }
}