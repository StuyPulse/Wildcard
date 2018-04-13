package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftMoveSpeedCommand extends Command {
    private double speed;

    public LiftMoveSpeedCommand(double speed) {
//        requires(Robot.lift);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        Robot.lift.moveDangerous(speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.lift.stop();
    }

}
