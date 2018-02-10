package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CrabArmFlapOutCommand extends Command {

    public CrabArmFlapOutCommand() {
        requires(Robot.crabArm);
    }

    protected void execute() {
        Robot.crabArm.flapOut();
    }

    protected boolean isFinished() {
        return false;
    }
}
