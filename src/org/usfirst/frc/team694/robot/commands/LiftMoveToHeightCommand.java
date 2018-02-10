package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LiftMoveToHeightCommand extends Command {
    private double height;
    private double startHeight;

    //It's in inches
    public LiftMoveToHeightCommand(double height) {
        this.height = height;
        requires(Robot.lift);
    }

    protected void initialize() {
        startHeight = Robot.lift.getLiftHeight();
    }

    protected void execute() {
        if (startHeight > height) {
            Robot.lift.moveLift(RobotMap.LIFT_MAX_SPEED * -1);
        } else if (startHeight < height) {
            Robot.lift.moveLift(RobotMap.LIFT_MAX_SPEED);
        }
    }

    protected boolean isFinished() {
        return (Math.abs(Robot.lift.getLiftHeight() - height) < .25);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}