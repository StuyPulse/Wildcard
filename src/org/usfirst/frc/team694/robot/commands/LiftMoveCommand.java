package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftMoveCommand extends Command {

    public LiftMoveCommand() {
        requires(Robot.lift);
        //requires(Robot.spatula);
    }

    protected void initialize() {
    }
    
    protected void execute() {
        double liftControl = Robot.oi.operatorGamepad.getLeftY();
        double liftSquared = Math.pow(liftControl, 2) * Math.signum(liftControl);
        
       // if(Robot.spatula.isSpatulaUp()) {
         //   Robot.spatula.acquireSpeed(-liftSquared * 0.2);
        //}
        
        Robot.lift.move(liftSquared);
        //TODO: see whether Coby wants cubed or squared inputs on the lift.
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }
}
