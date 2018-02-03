package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMap;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMaxUpCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SameSideScaleAutonCommand extends CommandGroup {
    public double speed = 0.5;
    public SameSideScaleAutonCommand() {
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE, speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(FieldMap.DEGREE_OF_ANGLE_TO_REACH_PLATFORM_ZONE_EDGE_FROM_AUTO_LINE));
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_TO_TRAVEL_TO_REACH_PLATFORM_ZONE_EDGE, speed));
        addSequential(new LiftMaxUpCommand());
        addSequential(new DrivetrainRotateDegreesPIDCommand(-FieldMap.DEGREE_OF_ANGLE_TO_REACH_PLATFORM_ZONE_EDGE_FROM_AUTO_LINE));
        addSequential(new GrabberOpenCommand());
    }
}
