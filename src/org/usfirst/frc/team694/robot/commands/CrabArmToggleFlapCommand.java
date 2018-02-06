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

    protected void initialize() {
        Robot.crabArm.flapToggle();
    }
}
