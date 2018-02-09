package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class LiftMoveUpHeightCommand extends Command {
    
    public double height;

    public LiftMoveUpHeightCommand(double height) {
        requires(Robot.lift);
        this.height = height;
    }

    protected void initialize() {
        Robot.lift.resetEncoders();
        Robot.lift.setBrakeOff();
    }

    protected void execute() {
        Robot.lift.moveLift(RobotMap.LIFT_MAX_SPEED);
    }

    protected boolean isFinished() {
        return Robot.lift.getLiftHeight() >= height;
    }

    protected void end() {
        Robot.lift.setBrakeOn();
        Robot.lift.resetEncoders();
        Robot.lift.stop();
    }

    protected void interrupted() {
    }
}