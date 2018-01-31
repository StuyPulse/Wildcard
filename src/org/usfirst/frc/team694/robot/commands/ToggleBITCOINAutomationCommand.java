package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ToggleBITCOINAutomationCommand extends InstantCommand {
    public ToggleBITCOINAutomationCommand() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
        Robot.isBITCOINAutomation = !(Robot.isBITCOINAutomation);
    }

}
