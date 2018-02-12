package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class LiftMoveCommand extends Command {

    public LiftMoveCommand() {
        requires(Robot.lift);
    }

    protected void execute() {
        double liftControlL = Robot.oi.operatorGamepad.getLeftY();
        double liftControlR = Robot.oi.operatorGamepad.getRightY();
        if (liftControlL < 0) {
            Robot.lift.move((liftControlL * liftControlL * -1) / 2);
        }
        if (liftControlL >= 0) {
            Robot.lift.move((liftControlL * liftControlL) / 2);
        }
        Robot.lift.move((liftControlR * liftControlR * liftControlR) / 2);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.lift.stop();
    }
}