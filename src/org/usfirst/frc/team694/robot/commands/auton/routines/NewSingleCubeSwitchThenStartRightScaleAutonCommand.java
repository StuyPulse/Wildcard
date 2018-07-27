package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NewSingleCubeSwitchThenStartRightScaleAutonCommand extends CommandGroup {

    public NewSingleCubeSwitchThenStartRightScaleAutonCommand(boolean isSwitchRight) {
        // Score 1st cube Switch
        addSequential(new SingleCubeSwitchAutonChooserCommand());

        // Grab 2nd cube
        addSequential(new PostSingleScoreSwitchGrabCubeAutonCommand(isSwitchRight, false));

        // Get in scale scoring position by getting out the way of the switch
        double SCALE_READY_ANGLE = 45 + 10;
        double SCALE_READY_DISTANCE = 24 + 24;

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(SCALE_READY_ANGLE));
        addSequential(new DrivetrainMoveInchesEncoderCommand(SCALE_READY_DISTANCE, 1));
        
        //going to the scale
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        addSequential(new DrivetrainMoveInchesEncoderCommand(200, 1));
    }
}
