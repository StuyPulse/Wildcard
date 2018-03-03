package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LiftMoveToHeightCommand extends Command {
    private double targetHeight;

    public LiftMoveToHeightCommand(double height) {
//        requires(Robot.lift);
        this.targetHeight = height;
    }

    @Override
    protected void initialize() {
        System.out.println("[LiftMoveToHeight] starto!");
    }

    @Override
    protected void execute() {
        Robot.lift.setHeight(targetHeight);
    }

    @Override
    protected boolean isFinished() {
        return (Math.abs(Robot.lift.getLiftHeight() - targetHeight) < RobotMap.LIFT_CLOSE_ENOUGH_HEIGHT_THRESHOLD)
                || (Robot.lift.isAtBottom() && Robot.lift.getMotorVelocity() < 0) 
                || (Robot.lift.isAtTop() && Robot.lift.getMotorVelocity() > 0);
    }

    @Override
    protected void end() {
        Robot.lift.stop();
        System.out.println("[LiftMoveToHeight] STOP");
    }
}