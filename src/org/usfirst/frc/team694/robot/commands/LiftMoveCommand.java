package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class LiftMoveCommand extends Command {
    double power = -1; //TODO: Figure out what power should be set to
    public LiftMoveCommand() {
        requires(Robot.lift);
    }

    protected void execute() {
        double liftControlL = Robot.oi.operatorGamepad.getLeftY();

        Robot.lift.move(Math.pow(liftControlL,SmartDashboard.getNumber("Lift Operator power control.", power)) * Math.signum(liftControlL));

        SmartDashboard.getNumber("Lift Operator power control", power);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.lift.stop();
    }
}