package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftMoveCommand extends Command {

    private double currentHeight;

    public LiftMoveCommand() {
        requires(Robot.lift);          
    }

    protected void execute() {
        double liftControl = Robot.oi.operatorGamepad.getLeftY();
          
        if (Math.abs(liftControl) > RobotMap.LIFT_JOYSTICK_MOVE_THRESHOLD ) {
            Robot.lift.move(Math.pow(liftControl, 2) * Math.signum(liftControl));
            currentHeight = Robot.lift.getLiftHeight();
            //TODO: see whether Coby wants cubed or squared inputs on the lift.
        } else {
            Robot.lift.setHeight(currentHeight);
        }
        
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }
}
