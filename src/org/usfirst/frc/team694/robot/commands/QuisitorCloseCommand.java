package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class QuisitorCloseCommand extends InstantCommand {

    public QuisitorCloseCommand() {
        requires(Robot.quisitor);
    }

    @Override
    protected void initialize() {
        Robot.quisitor.close();
    }
}