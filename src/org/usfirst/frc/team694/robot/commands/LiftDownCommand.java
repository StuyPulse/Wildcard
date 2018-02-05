package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftDownCommand extends Command {

    public LiftDownCommand() {
        requires(Robot.lift);
    }

    protected void initialize() {
        Robot.lift.setBrakeOff();
    }

    protected void execute() {
        Robot.lift.goDown();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        Robot.lift.setBrakeOn();
    }
}