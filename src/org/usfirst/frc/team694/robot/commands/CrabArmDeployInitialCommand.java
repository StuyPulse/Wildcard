package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class CrabArmDeployInitialCommand extends InstantCommand {

    public CrabArmDeployInitialCommand() {
        super();
        requires(Robot.crabArm);
    }
    
    protected void initialize() {
        Robot.crabArm.deploy();
    }
}