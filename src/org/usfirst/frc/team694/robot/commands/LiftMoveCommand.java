package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftMoveCommand extends Command {

    private static final double GAMEPAD_LIFT_THRESHOLD = 0.1;

    public LiftMoveCommand() {
        requires(Robot.lift);
        // Problems with default command overriding spatula:
//        requires(Robot.spatula);
    }

    protected void initialize() {
        // Don't run during auton
        if (Robot.getInstance().isAutonomous()) {
            cancel();
        }
    }

    protected void execute() {
        double liftControl = Robot.oi.operatorGamepad.getLeftY();
        double liftSquared = Math.pow(liftControl, 2) * Math.signum(liftControl);


        //TODO: see whether Coby wants cubed or squared inputs on the lift.

        if (Math.abs(liftControl) > GAMEPAD_LIFT_THRESHOLD) {
            Robot.lift.move(liftSquared);
//            Robot.drivetrain.enableCurrentLimit();
        } else {
            Robot.lift.stop();
//            Robot.drivetrain.disableCurrentLimit();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }
}
