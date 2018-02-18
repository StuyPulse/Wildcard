package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CrabArmAcquireCommand extends Command {

    public CrabArmAcquireCommand() {
        requires(Robot.crabArm);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.crabArm.acquire();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.crabArm.stop();
    }

}