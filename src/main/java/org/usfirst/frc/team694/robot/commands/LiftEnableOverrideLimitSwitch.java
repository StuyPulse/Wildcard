package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LiftEnableOverrideLimitSwitch extends InstantCommand {

    public LiftEnableOverrideLimitSwitch() {
    }

    @Override
    protected void initialize() {
        Robot.lift.enableOverrideLimitSwitch();
    }

}
