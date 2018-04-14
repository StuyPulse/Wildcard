package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * This is mainly for our alliance partners, but this should make the 
 * robot pause for 10 seconds and then start moving the mobility distance
 */
public class DelayedMobilityAutonCommand extends CommandGroup {
    private static final double MOBILITY_DISTANCE = 120 + RobotMap.LENGTH_OF_BOT + 10;
    
    public DelayedMobilityAutonCommand() {
        addSequential(new WaitCommand(10));
        addSequential(new DrivetrainMoveInchesEncoderCommand(MOBILITY_DISTANCE, 0.7));
    }
}
