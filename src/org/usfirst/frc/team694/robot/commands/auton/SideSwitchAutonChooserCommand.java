package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SideSwitchAutonChooserCommand extends ConditionalCommand {

    public SideSwitchAutonChooserCommand() {
        //TODO: Add a different side scale auton
        super(new SameSideSwitchAutonCommand());
    }

    @Override
    protected boolean condition() {
        return Robot.isRobotAndSwitchOnSameSide();
    }
}