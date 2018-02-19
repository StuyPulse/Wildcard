package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SideSwitchAutonChooserCommand extends ConditionalCommand {

    public SideSwitchAutonChooserCommand() {
        super(new SameSideSwitchAutonCommand(), new MobilityAutonUsingEncodersCommand()); //TODO: Change mobility auton for different side switch auton
    }

    @Override
    protected boolean condition() {
        return Robot.isRobotAndSwitchOnSameSide();
    }
}