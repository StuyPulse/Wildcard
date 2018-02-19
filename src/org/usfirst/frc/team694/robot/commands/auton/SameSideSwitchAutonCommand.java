package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SameSideSwitchAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();
    private double speed = 0.75;

    public SameSideSwitchAutonCommand() {
        //TODO replace 150 with field map variable, 150 is the length of the alliance station to the same side switch + 10
        addSequential(new DriveStraightPIDCommand(quad.getDistanceToDriveForwardToReachSwitchSide(), speed));

    }
}
