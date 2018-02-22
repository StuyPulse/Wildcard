package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CrabArmAcquireCommand extends Command {

    public CrabArmAcquireCommand() {
        requires(Robot.crabArm);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.crabArm.acquire();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.crabArm.stop();
    }

}