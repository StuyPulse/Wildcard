package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class AcquirerTongsTightenHoldCommand extends InstantCommand {
    
    public AcquirerTongsTightenHoldCommand() {
        super();
        requires(Robot.spatula);
    }

    protected void initialize() {
        Robot.spatula.tightenCubeGrip();
    }
}