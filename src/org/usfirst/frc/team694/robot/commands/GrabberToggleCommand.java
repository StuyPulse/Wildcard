package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * If the grabber is open, close it and vice versa
 */
public class GrabberToggleCommand extends InstantCommand {

    public GrabberToggleCommand() {
        super();
        requires(Robot.grabber);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.grabber.toggle();
    }
}