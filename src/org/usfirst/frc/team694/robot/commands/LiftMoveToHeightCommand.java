package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

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
        // Original:
        //        Robot.lift.setHeight(targetHeight);
        if (targetHeight > Robot.lift.getLiftHeight()) {
            Robot.lift.moveRamp(1);
        }
        //         else {
        //            Robot.lift.move(-1);
        //        }
    }

    @Override
    protected boolean isFinished() {
        double deltaTarget = targetHeight - Robot.lift.getLiftHeight();
        // Original
        //        return (Math.abs(Robot.lift.getLiftHeight() - targetHeight) < RobotMap.LIFT_CLOSE_ENOUGH_HEIGHT_THRESHOLD)
        return (deltaTarget < 0) || (Robot.lift.isAtBottom() && deltaTarget < 0)
                || (Robot.lift.isAtTop() && deltaTarget > 0);
    }

    @Override
    protected void end() {
        Robot.lift.stop();
        System.out.println("[LiftMoveToHeight] STOP " + Robot.lift.getLiftHeight());
    }

    @Override
    protected void interrupted() {
        end();
        System.out.println("[LiftMoveToHeight] INTERRUPTED (ruh roh)");
    }

}