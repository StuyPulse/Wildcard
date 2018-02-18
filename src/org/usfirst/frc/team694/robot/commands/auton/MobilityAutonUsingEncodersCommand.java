package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MobilityAutonUsingEncodersCommand extends CommandGroup {

    public MobilityAutonUsingEncodersCommand() {
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.75, 120 + RobotMap.LENGTH_OF_BOT + 5));
    }
}