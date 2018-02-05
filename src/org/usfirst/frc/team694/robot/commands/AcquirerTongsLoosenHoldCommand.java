package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class AcquirerTongsLoosenHoldCommand extends InstantCommand {

    public AcquirerTongsLoosenHoldCommand() {
        super();
        requires(Robot.spatula);
    }

    protected void initialize() {
        Robot.spatula.loosenCubeGrip();
    }
}
