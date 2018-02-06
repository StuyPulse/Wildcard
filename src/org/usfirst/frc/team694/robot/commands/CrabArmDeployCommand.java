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

    protected void initialize() {
        //TODO: Figure out how long we want to run the acquirer
        setTimeout(4);
    }

    protected void execute() {
        //TODO: Figure out what direction the crabArms should run to deploy
        Robot.crabArm.acquire();
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        Robot.crabArm.stop();
    }

    protected void interrupted() {
    }
}
