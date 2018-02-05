package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class AcquirerFlipUpCommand extends InstantCommand {

    public AcquirerFlipUpCommand() {
        super();
        requires(Robot.spatula);
    }

    protected void initialize() {
        Robot.spatula.flipUp();
    }
}
