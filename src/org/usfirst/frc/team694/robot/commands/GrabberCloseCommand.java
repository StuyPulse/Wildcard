package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class GrabberCloseCommand extends InstantCommand {

    public GrabberCloseCommand() {
        requires(Robot.grabber);
    }

    protected void initialize() {
        Robot.grabber.close();
    }
}