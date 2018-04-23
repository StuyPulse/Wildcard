package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.DoubleCubeSwitchAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class DoubleCubeSwitchAutonChooserCommand extends ConditionalCommand {

    public DoubleCubeSwitchAutonChooserCommand() {
        super(new DoubleCubeSwitchAutonCommand(true), new DoubleCubeSwitchAutonCommand(false));
    }

    @Override
    public boolean condition() {
        return Robot.isRobotOnRight();
    }
}
