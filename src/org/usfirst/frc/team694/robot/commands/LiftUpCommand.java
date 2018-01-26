package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LiftUpCommand extends InstantCommand {

    public LiftUpCommand() {
        super();
        requires(Robot.lift);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
        Robot.lift.setBrakeOff();
        Robot.lift.goUp();
    }
}
