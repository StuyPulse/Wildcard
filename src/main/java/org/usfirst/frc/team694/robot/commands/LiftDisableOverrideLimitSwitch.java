package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LiftDisableOverrideLimitSwitch extends InstantCommand {

    public LiftDisableOverrideLimitSwitch() {
    }

    @Override
    protected void initialize() {
        Robot.lift.disableOverrideLimitSwitch();
    }

}
