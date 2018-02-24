package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class LiftStopCommand extends InstantCommand {

    public LiftStopCommand() {
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        Robot.lift.stop();
    }
}