package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class AcquirerTongsLoosenHoldCommand extends InstantCommand {

    public AcquirerTongsLoosenHoldCommand() {
        super();
        requires(Robot.acquirer);
    }

    protected void initialize() {
        Robot.acquirer.loosenCubeGrip();
    }
}
