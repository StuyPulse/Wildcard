package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class CrabArmFlapOutCommand extends InstantCommand {

    public CrabArmFlapOutCommand() {
        super();
        requires(Robot.crabArm);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
        Robot.crabArm.flapOut();
    }

}
