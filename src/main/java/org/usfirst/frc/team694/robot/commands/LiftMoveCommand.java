package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.subsystems.Lift;

/**
 *
 */
public class LiftMoveCommand extends DefaultCommand {

    private static final double GAMEPAD_LIFT_THRESHOLD = 0.1;

    public LiftMoveCommand() {
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    // TODO: This is getting out of hand
    //       Please fix DriveDistanceEncodersCommand
    @Override
    protected void defaultExecute() {

        double liftControl = Robot.oi.operatorGamepad.getLeftY();
        double liftSquared = Math.pow(liftControl, 2) * Math.signum(liftControl);

        if (Math.abs(liftControl) > GAMEPAD_LIFT_THRESHOLD) {
            //Robot.lift.moveDangerous(liftSquared);
            if (Lift.rampDisabled) {
                Robot.lift.moveDangerous(liftSquared);
            } else {
                Robot.lift.moveRamp(liftSquared);
            }
//            Robot.drivetrain.enableCurrentLimit();
        } else {
            Robot.lift.stop();
//            Robot.drivetrain.disableCurrentLimit();
        }
//        System.out.println("[LiftMoveCommand] output: " + liftSquared);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }
}
