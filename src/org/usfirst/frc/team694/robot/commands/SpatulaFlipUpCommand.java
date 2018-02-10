package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SpatulaFlipUpCommand extends InstantCommand {

    public SpatulaFlipUpCommand() {
        super();
        requires(Robot.spatula);
    }

    protected void initialize() {
        Robot.spatula.flipUp();
    }
}
//Should Probably not run this on its own, the crabarms have to move out of the way first