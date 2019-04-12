package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NewSingleCubeSwitchThenStartLeftScaleAutonCommand extends CommandGroup {

    public NewSingleCubeSwitchThenStartLeftScaleAutonCommand(boolean isSwitchRight) {
        // Score 1st cube Switch
        addSequential(new SingleCubeSwitchAutonChooserCommand());

        // Grab 2nd cube
        addSequential(new PostSingleScoreSwitchGrabCubeAutonCommand(isSwitchRight, false));

        // Get in scale scoring position by clearing out of the way of the switch
        double SCALE_READY_ANGLE = 45 + 10;
        double SCALE_READY_DISTANCE = 24 + 24 + 12;
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(-1 * SCALE_READY_ANGLE), 1);
        addSequential(new DrivetrainMoveInchesEncoderCommand(SCALE_READY_DISTANCE, 1));

        //Going to the scale
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        addParallel(new LiftMoveToHeightCommand(5.0));
        addSequential(new DrivetrainMoveInchesEncoderCommand(200, 1));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand((60-15)), 1.5);
        addSequential(new LiftMoveToHeightCommand(86));
    }
}
