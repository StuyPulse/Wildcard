package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

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
        if (Robot.oi.operatorGamepad.getLeftY() > 0.9) {
            isHeightSet = false;
            Robot.lift.move(RobotMap.LIFT_MAX_SPEED);
        } else if (Robot.oi.operatorGamepad.getLeftY() > 0.4) {
            isHeightSet = false;
            Robot.lift.move(RobotMap.LIFT_MAX_SPEED / 2);
        } else if (Robot.oi.operatorGamepad.getLeftY() > -0.4) {
            Robot.lift.stop();
            currentHeight = Robot.lift.getLiftHeight();
            isHeightSet = true;
        } else if (Robot.oi.operatorGamepad.getLeftY() > -0.9) {
            isHeightSet = false;
            Robot.lift.move(RobotMap.LIFT_MAX_SPEED / 2 * -1);
        } else {
            Robot.lift.setHeight(currentHeight);
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.lift.stop();
    }
}