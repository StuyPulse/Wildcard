package org.usfirst.frc.team694.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MobilityAutonUsingEncodersCommand extends CommandGroup {

    public MobilityAutonUsingEncodersCommand() {
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.5, 168));
    }
}