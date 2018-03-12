package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Should Probably not run this on its own, the crabarms have to move out of the way first
 */
public class QuisitorFlipUpCommand extends InstantCommand {

    public QuisitorFlipUpCommand() {
        //TESTING: requires(Robot.spatula);
    }

    @Override
    protected void initialize() {
        Robot.quisitor.flipUp();
    }
}
