package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DrivePastLineCommand extends CommandGroup{

    public DrivePastLineCommand() {
        addSequential(new DrivetrainMoveInchesEncoderCommand(0.25, 0.67));//speed,distance (inch)
        addParallel(new DrivetrainLineSensorCommand(0.25,6.7));//Speed,Distance (inch)
        // Add Commands here:TODO: edit encoder command with Ramping PID command!
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
