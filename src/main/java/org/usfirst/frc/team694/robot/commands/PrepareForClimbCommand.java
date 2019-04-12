package org.usfirst.frc.team694.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PrepareForClimbCommand extends CommandGroup {

    public PrepareForClimbCommand() {
        addSequential(new QuisitorOpenCommand());
        addSequential(new LiftMoveToHeightCommand(65));
    }

}
