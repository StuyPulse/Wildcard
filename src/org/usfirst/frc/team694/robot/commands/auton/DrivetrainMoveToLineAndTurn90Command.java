package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.DrivetrainMoveToLineCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DrivetrainMoveToLineAndTurn90Command extends CommandGroup {

    public DrivetrainMoveToLineAndTurn90Command() {
        requires(Robot.drivetrain);
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
        addSequential(new DrivetrainMoveToLineCommand(87.5, 0.5));
        addSequential(new DrivetrainRotateDegreesPIDCommand(90));
    }
}
