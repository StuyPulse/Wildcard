package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AcquirerAcquireCommand extends Command {

    public AcquirerAcquireCommand() {
        requires(Robot.spatula);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        Robot.spatula.acquire();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
