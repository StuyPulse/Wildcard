package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMaxUpCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SameSideScaleAutonCommand extends CommandGroup {

    public SameSideScaleAutonCommand() {
        addSequential(new DrivetrainMoveToLineCommand(speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(degrees));
        addSequential(new DrivetrainMoveInchesEncoderCommand(hypothenuse, speed));
        addParallel(new LiftMaxUpCommand());
        addSequential(new DrivetrainRotateDegreesPIDCommand(-degrees));
        addSequential(new GrabberOpenCommand());
    }
}
