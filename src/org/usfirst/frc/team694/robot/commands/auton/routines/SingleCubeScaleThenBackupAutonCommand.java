package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.auton.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeScaleAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This should only run if we're scoring same side cause we shouldn't need to back
 * up when scoring opposite side scale
 */
public class SingleCubeScaleThenBackupAutonCommand extends CommandGroup {

    public SingleCubeScaleThenBackupAutonCommand(boolean isRight) {
        addSequential(new SingleCubeScaleAutonChooserCommand());
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        addSequential(new DriveStraightWithRampingCommand(261 + 12 + 6), -3.5);
    }
}
