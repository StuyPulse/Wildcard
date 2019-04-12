package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.FasterSingleCubeSwitchAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class FasterSingleCubeSwitchAutonChooserCommand extends ConditionalCommand{

    public FasterSingleCubeSwitchAutonChooserCommand() {
        super(new FasterSingleCubeSwitchAutonCommand(true), new FasterSingleCubeSwitchAutonCommand(false));
    }
    
    public boolean condition() {
        return Robot.isSwitchOnRight();
    }
}
