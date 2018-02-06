package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftMoveToHeightCommand extends Command {
    private double height;
    private double startHeight;

    public LiftMoveToHeightCommand(double height) {
        this.height = height;
        requires(Robot.lift);
    }

    protected void initialize() {
        startHeight = Robot.lift.getLiftHeight();
        Robot.lift.resetEncoders();
        Robot.lift.setBrakeOff();
    }

    protected void execute() {
        if (startHeight > height) {
            Robot.lift.goDown();
        } else if (startHeight < height) {
            Robot.lift.goUp();
        }
    }

    protected boolean isFinished() {
        return (Robot.lift.getLiftHeight() == height);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}