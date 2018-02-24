package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MobilityAutonCommand extends CommandGroup {
    // TODO: Incorporate with FieldMapQuadrants
    private static final double MOBILITY_DISTANCE = 120 + RobotMap.LENGTH_OF_BOT + 10;

    public MobilityAutonCommand() {
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.7, MOBILITY_DISTANCE));
    }
}