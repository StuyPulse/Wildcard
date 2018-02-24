package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftMoveCommand extends Command {

    private static final double DRIVETRAIN_LIMIT_LIFT_THRESHOLD = 0.1;

    public LiftMoveCommand() {
        requires(Robot.lift);
        requires(Robot.spatula);
    }

    protected void initialize() {
    }

    protected void execute() {
        double liftControl = Robot.oi.operatorGamepad.getLeftY();
        double liftSquared = Math.pow(liftControl, 2) * Math.signum(liftControl);

        if (Robot.spatula.isSpatulaUp()) {
            Robot.spatula.acquireSpeed(-liftSquared * 0.2);
        }

        //TODO: see whether Coby wants cubed or squared inputs on the lift.

        if (Math.abs(liftControl) > DRIVETRAIN_LIMIT_LIFT_THRESHOLD) {
            Robot.lift.move(liftSquared);
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }
}
