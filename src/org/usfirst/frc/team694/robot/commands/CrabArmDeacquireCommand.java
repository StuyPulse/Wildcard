package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CrabArmDeacquireCommand extends Command {

    public CrabArmDeacquireCommand() {
        requires(Robot.crabArm);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.crabArm.flapOut();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}