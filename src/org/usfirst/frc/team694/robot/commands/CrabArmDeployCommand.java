package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CrabArmDeployCommand extends Command {

    public CrabArmDeployCommand() {
        requires(Robot.crabArm);
    }

    @Override
    protected void initialize() {
        //TODO: Figure out how long we want to run the spatula
        setTimeout(4);
    }

    @Override
    protected void execute() {
        //TODO: Figure out what direction the crabArms should run to deploy
        Robot.crabArm.acquire();
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    @Override
    protected void end() {
        Robot.crabArm.stop();
    }

    @Override
    protected void interrupted() {
    }
}
