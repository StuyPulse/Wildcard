package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class GrabberCloseCommand extends InstantCommand {

    public GrabberCloseCommand() {
        requires(Robot.grabber);
    }

    @Override
    protected void initialize() {
        Robot.grabber.close();
    }
}