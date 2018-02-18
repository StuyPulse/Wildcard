package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpatulaAcquireCommand extends Command {

    public SpatulaAcquireCommand() {
        requires(Robot.spatula);
    }
    
    @Override
    protected void initialize() {
        System.out.println("[SpatulaAcquireCommand] START!");
    }

    @Override
    protected void execute() {
        Robot.spatula.acquire();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        System.out.println("[SpatulaAcquireCommand] STOP!");
        Robot.spatula.stop();
    }
}
