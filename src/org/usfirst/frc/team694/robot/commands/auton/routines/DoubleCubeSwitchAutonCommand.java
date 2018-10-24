package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DoubleCubeSwitchAutonCommand extends CommandGroup {
//    private static final double DISTANCE_TO_CUBE = 100;
//    private static final double DISTANCE_TO_SWITCH = 100;

    private static final double SWITCH_READY_ANGLE = 45;
    private static final double SWITCH_READY_DISTANCE = 34;

    private static final double SWITCH_SCORE_DISTANCE = 54;

    public DoubleCubeSwitchAutonCommand(boolean isSwitchRight) {

        addSequential(new SingleCubeSwitchAutonChooserCommand());

        addSequential(new PostSingleScoreSwitchGrabCubeAutonCommand(isSwitchRight, false));

        // Get in 2nd cube Switch scoring position

        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isSwitchRight ? SWITCH_READY_ANGLE : (-1 * SWITCH_READY_ANGLE)), .8);
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30.0), 15));
        addSequential(new DrivetrainMoveInchesEncoderCommand(SWITCH_READY_DISTANCE + (isSwitchRight? 0 : 10), 1));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0), 1);

        // Score that 2nd bad boy
        addSequential(new DrivetrainMoveInchesEncoderCommand(SWITCH_SCORE_DISTANCE - (isSwitchRight ? 0 : 10.0/1.4), 0.3), 1);
        //addSequential(new DrivetrainDriveCurveCommand(SWITCH_SCORE_DISTANCE, RampMode.NO_RAMP_UP), 2.0);
        addSequential(new WaitCommand(.25));
        addSequential(new QuisitorDeacquireCommand(), 0.75);
        addSequential(new DrivetrainMoveInchesEncoderCommand(10 + 5, -1)); // -0.45
    }
}
