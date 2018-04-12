package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OldDifferentSideScaleAutonCommand extends CommandGroup {

    public OldDifferentSideScaleAutonCommand() {
        addSequential(new DriveStraightWithRampingCommand(209.935)); //Retrieved from Field Map

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(Robot.isRobotStartingOnRight()? -90 : 90));
        addSequential(new DriveStraightWithRampingCommand(185.31));

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));

        addSequential(new LiftMoveToHeightCommand(84));//unsure about height
        addSequential(new DriveStraightWithRampingCommand(71)); //We will incur fouls if we keep this, we should change this.
        addSequential(new QuisitorOpenCommand());
        //This does not use new shooting functionality from the Quisitor
    }
}
