package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class BITCOINAutomationOnCommand extends InstantCommand {
    public BITCOINAutomationOnCommand() {
        super();
    }

    protected void initialize() {
        Robot.quisitor.isBITCOINAutomation = true;
    }
}
