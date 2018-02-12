package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

        Robot.lift.move(Math.pow(liftControlL,SmartDashboard.getNumber("Lift Operator power control.", power) * signum(liftControlL));

        SmartDashboard.getNumber("Coby is an idiot");

    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.lift.stop();
    }
}
