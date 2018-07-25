package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.DoubleCubeSwitchThenGrabAnotherAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class DoubleCubeSwitchThenGrabAnotherAutonChooserCommand extends ConditionalCommand {

    public DoubleCubeSwitchThenGrabAnotherAutonChooserCommand() {
        super(new DoubleCubeSwitchThenGrabAnotherAutonCommand(true), new DoubleCubeSwitchThenGrabAnotherAutonCommand(false));
    }
    
    public boolean condition() {
        return Robot.isSwitchOnRight();
    }
}
