package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class BITCOINAutomationOffCommand extends InstantCommand {

    public BITCOINAutomationOffCommand() {
        super();
    }

    @Override
    protected void initialize() {
        Robot.spatula.isBITCOINAutomation = false;
    }
}