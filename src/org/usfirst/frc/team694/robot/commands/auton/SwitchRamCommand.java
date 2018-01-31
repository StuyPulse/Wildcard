package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMap;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchRamCommand extends CommandGroup {

    public SwitchRamCommand() {
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.5, 0.5 * FieldMap.DISTANCE_FROM_ALLIANCE_STATION_TO_SWITCH));
        addSequential(new DrivetrainRotateDegreesPIDCommand(((DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') ? -1 : 1) * 35));
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
