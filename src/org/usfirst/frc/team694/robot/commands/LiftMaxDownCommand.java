package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftMaxDownCommand extends Command {

    public LiftMaxDownCommand() {
        requires(Robot.lift);
    }
    
    protected void initialize() {
        Robot.lift.setBrakeOff();
    }

    protected void execute() {
        Robot.lift.goDown();
    }

    protected boolean isFinished() {
        return Robot.lift.isAtBottom();
    }

    protected void end() {
        Robot.lift.stop();
        Robot.lift.setBrakeOn();
    }

    protected void interrupted() {
    }
}