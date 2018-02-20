package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LiftMoveToHeightCommand extends Command {
    private double targetHeight;

    //It's in inches
    public LiftMoveToHeightCommand(double height) {
        requires(Robot.lift);
        this.targetHeight = height;
    }

    protected void initialize() {
        Robot.lift.temporarySetkP(SmartDashboard.getNumber("Lift P", 0));
        System.out.println("[LiftMoveToHeight] START");
    }

    protected void execute() {
        Robot.lift.setHeight(targetHeight);
    }

    protected boolean isFinished() {
        return (Math.abs(Robot.lift.getLiftHeight() - targetHeight) < RobotMap.LIFT_CLOSE_ENOUGH_HEIGHT_THRESHOLD)
                || (Robot.lift.isAtBottom() && Robot.lift.getMotorVelocity() < 0) 
                || (Robot.lift.isAtTop() && Robot.lift.getMotorVelocity() > 0);
    }

    protected void end() {
        System.out.println("[LiftMoveToHeight] END");
        Robot.lift.stop();
    }
}