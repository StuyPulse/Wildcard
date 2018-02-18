package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class GrabberOpenCommand extends InstantCommand {

    public GrabberOpenCommand() {
        requires(Robot.grabber);
    }

    @Override
    protected void initialize() {
        Robot.grabber.open();
    }
}