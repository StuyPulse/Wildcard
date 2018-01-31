package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Stop the lift and set the brake on
 */
public class LiftStopCommand extends InstantCommand {

    public LiftStopCommand() {
        super();
        requires(Robot.lift);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
        Robot.lift.stop();
        Robot.lift.setBrakeOn();
    }

}
