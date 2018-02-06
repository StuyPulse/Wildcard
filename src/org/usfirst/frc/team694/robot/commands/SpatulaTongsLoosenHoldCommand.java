package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SpatulaTongsLoosenHoldCommand extends InstantCommand {

    public SpatulaTongsLoosenHoldCommand() {
        super();
        requires(Robot.spatula);
    }

    protected void initialize() {
        Robot.spatula.loosenCubeGrip();
    }
}
