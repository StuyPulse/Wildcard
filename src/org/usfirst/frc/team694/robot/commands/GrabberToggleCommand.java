package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class GrabberToggleCommand extends InstantCommand {

    public GrabberToggleCommand() {
        requires(Robot.grabber);
    }

    @Override
    protected void initialize() {
        System.out.println("[GrabberToggleCommand] toggle");
        Robot.grabber.toggle();
    }
}