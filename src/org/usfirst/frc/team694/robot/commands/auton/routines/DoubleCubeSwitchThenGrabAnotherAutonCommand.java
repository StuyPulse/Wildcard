package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.auton.choosers.FasterSingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * UNFINISHED
 */
public class DoubleCubeSwitchThenGrabAnotherAutonCommand extends CommandGroup {

    public DoubleCubeSwitchThenGrabAnotherAutonCommand(boolean isSwitchRight) {
        addSequential(new FasterSingleCubeSwitchAutonChooserCommand());
        addSequential(new FasterPostSingleScoreSwitchGrabCubeAutonCommand(isSwitchRight));
        
    }
}
