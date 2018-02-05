package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class LiftStopCommand extends InstantCommand {

    public LiftStopCommand() {
        super();
        requires(Robot.lift);
    }

    protected void initialize() {
        Robot.lift.stop();
    }
}