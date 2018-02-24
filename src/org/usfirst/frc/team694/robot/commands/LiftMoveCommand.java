package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftMoveCommand extends Command {

    private static final double DRIVETRAIN_LIMIT_LIFT_THRESHOLD = 0.1;

    public LiftMoveCommand() {
        requires(Robot.lift);
        // Problems with default command overriding spatula:
//        requires(Robot.spatula);
    }

    protected void initialize() {
    }

    protected void execute() {
        double liftControl = Robot.oi.operatorGamepad.getLeftY();
        double liftSquared = Math.pow(liftControl, 2) * Math.signum(liftControl);


        Robot.lift.move(liftSquared);
        //TODO: see whether Coby wants cubed or squared inputs on the lift.

        if (Math.abs(liftControl) > DRIVETRAIN_LIMIT_LIFT_THRESHOLD) {
            if (Robot.spatula.isSpatulaUp()) {
                Robot.spatula.acquireSpeed(-liftSquared * 0.2);
            }
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }
}
