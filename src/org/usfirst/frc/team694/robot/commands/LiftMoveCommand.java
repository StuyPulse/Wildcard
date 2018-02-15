package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class LiftMoveCommand extends Command {

    private double currentHeight;
    private boolean isHeightSet;

    public LiftMoveCommand() {
        requires(Robot.lift);
        this.currentHeight = currentHeight;
        this.isHeightSet = isHeightSet;
        isHeightSet = false;
               
    }

    protected void execute() {
        double liftControl = Robot.oi.operatorGamepad.getLeftY();
        
        Robot.lift.move(Math.pow(liftControl, 2) * Math.signum(liftControl));
        //TODO: see whether Coby wants cubed or squared inputs on the lift.
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }
}
