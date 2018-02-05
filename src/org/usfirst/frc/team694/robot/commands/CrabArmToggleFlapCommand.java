package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class CrabArmToggleFlapCommand extends InstantCommand {

    public CrabArmToggleFlapCommand() {
        requires(Robot.crabArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.crabArm.flapToggle();
    }
}
