package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LiftMoveToHeightCommand extends Command {
    private double targetHeight;

    public LiftMoveToHeightCommand(double height) {
        requires(Robot.lift);
        this.targetHeight = height;
    }

    protected void execute() {
        double currentHeight = Robot.lift.getLiftHeight();
        if (currentHeight > targetHeight) {
            Robot.lift.move(RobotMap.LIFT_MAX_SPEED *  -1);
        } else {
            Robot.lift.move(RobotMap.LIFT_MAX_SPEED);
        }
    }

    protected boolean isFinished() {
        return (Math.abs(Robot.lift.getLiftHeight() - targetHeight) < RobotMap.LIFT_HEIGHT_TOLERANCE);
    }

    protected void end() {
        Robot.lift.stop();
    }
}