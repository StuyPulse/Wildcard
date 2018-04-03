package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SingleCubeSwitchThenStartScaleAutonChooserCommand extends ConditionalCommand {

    public SingleCubeSwitchThenStartScaleAutonChooserCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super(new SingleCubeSwitchThenStartScaleAutonCommand(true), new SingleCubeSwitchThenStartScaleAutonCommand(false));
    }

    @Override
    public boolean condition() {
        return Robot.isSwitchOnRight();
    }
}
