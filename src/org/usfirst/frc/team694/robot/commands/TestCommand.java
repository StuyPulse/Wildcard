package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

public class TestCommand extends CommandGroup {

    public TestCommand() {
        // Move back while lifting to give cube clearance, then move forward
        double BACKUP_DISTANCE = 5 + 20;
        double BACKUP_SPEED = 0.2;

        addParallel(new DrivetrainMoveInchesEncoderCommand(BACKUP_DISTANCE, -1 * BACKUP_SPEED));
        addSequential(new LiftMoveToHeightCommand(83.0));

    }
}
