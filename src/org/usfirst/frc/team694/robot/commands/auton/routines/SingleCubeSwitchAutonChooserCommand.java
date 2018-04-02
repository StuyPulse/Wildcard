package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SingleCubeSwitchAutonChooserCommand extends ConditionalCommand {

    public SingleCubeSwitchAutonChooserCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(new SingleCubeSwitchAutonCommand(true), new SingleCubeSwitchAutonCommand(false));
    }

    @Override
    public boolean condition() {
        return Robot.isSwitchOnRight();
    }
}