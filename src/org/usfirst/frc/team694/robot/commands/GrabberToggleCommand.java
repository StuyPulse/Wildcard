package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class GrabberToggleCommand extends InstantCommand {

    public GrabberToggleCommand() {
        super();
        requires(Robot.grabber);
    }

    protected void initialize() {
        Robot.grabber.toggle();
    }
}