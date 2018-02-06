package org.usfirst.frc.team694.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivetrainMobilityAutonCommand extends CommandGroup {

    public DrivetrainMobilityAutonCommand() {
        addSequential(new DrivetrainMoveToLineCommand(60.0,0.25));
    }
}