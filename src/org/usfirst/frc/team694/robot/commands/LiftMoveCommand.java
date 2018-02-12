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

        Robot.lift.move(liftControlL * liftControlL * signum(liftControlL));
        Robot.lift.move(liftControlR * liftControlR * liftControlR);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.lift.stop();
    }
}
