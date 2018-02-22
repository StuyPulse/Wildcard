package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpatulaAcquireCommand extends Command {

    public SpatulaAcquireCommand() {
        requires(Robot.spatula);
    }
    
    protected void initialize() {
        System.out.println("[SpatulaAcquireCommand] START!");
    }

    protected void execute() {
        Robot.spatula.acquire();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        System.out.println("[SpatulaAcquireCommand] STOP!");
        Robot.spatula.stop();
    }
}
