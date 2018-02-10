package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMap;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SameSideRightAngleAutonCommand extends CommandGroup {
    public double speed = 0.5;
    public SameSideRightAngleAutonCommand() {
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE, speed));
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_FROM_AUTO_LINE_TO_NULL_TERRITORY, speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(FieldMap.DEGREE_OF_ANGLE_TO_REACH_IDEAL_STARTING_POINT_FROM_NULL_TERRITORY_LINE));
        addSequential(new DrivetrainMoveToLineCommand(FieldMap.DISTANCE_TO_TRAVEL_TO_REACH_SCALE_CORNER, 0.2));
        addParallel(new LiftMoveToHeightCommand(84));
        addSequential(new GrabberOpenCommand());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
