package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

/**
 *
 */
public class QuisitorMoveControlCommand extends DefaultCommand {

    public QuisitorMoveControlCommand() {
        requires(Robot.quisitor);
    }

    protected void initialize() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    @Override
    protected void defaultExecute() {
        // TODO Auto-generated method stub
        double acquireSpeed = Math.pow(Robot.oi.operatorGamepad.getRawRightTriggerAxis(), 3);
        double deacquireSpeed = Math.pow(Robot.oi.operatorGamepad.getRawLeftTriggerAxis(), 3);
        
        Robot.quisitor.acquireSpeed(acquireSpeed - deacquireSpeed);
    }
}
