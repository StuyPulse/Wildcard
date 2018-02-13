package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpatulaDeacquireCommand extends Command {

    public SpatulaDeacquireCommand() {
        requires(Robot.spatula);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        Robot.spatula.deacquire();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.spatula.stop();
    }
}
