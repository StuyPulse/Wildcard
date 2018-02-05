package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftMaxUpCommand extends Command {

    public LiftMaxUpCommand() {
        requires(Robot.lift);
    }

    protected void initialize() {
        Robot.lift.setBrakeOff();
    }

    protected void execute() {
        Robot.lift.goUp();
    }

    protected boolean isFinished() {
        return Robot.lift.isAtTop();
    }

    protected void end() {
        Robot.lift.stop();
        Robot.lift.setBrakeOn();
    }

    protected void interrupted() {
    }
}