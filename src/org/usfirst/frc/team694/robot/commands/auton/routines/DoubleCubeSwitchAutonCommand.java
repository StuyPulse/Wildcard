package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.choosers.SingleCubeSwitchAutonChooserCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleCubeSwitchAutonCommand extends CommandGroup {
    private static final double DISTANCE_TO_CUBE = 100;
    private static final double DISTANCE_TO_SWITCH = 100;

    public DoubleCubeSwitchAutonCommand() {
        DrivetrainDriveCurveCommand curveToCube = new DrivetrainDriveCurveCommand(DISTANCE_TO_CUBE);
        curveToCube.addSpeedChange(0, -0.6);
        curveToCube.addTurn(40, 90);

        DrivetrainDriveCurveCommand curveToSwitch = new DrivetrainDriveCurveCommand(DISTANCE_TO_SWITCH);
        curveToSwitch.addSpeedChange(0, 0.6);
        curveToSwitch.addTurn(40, 0);

        addSequential(new SingleCubeSwitchAutonChooserCommand());
        addSequential(curveToSwitch, 5); //TODO: Is this the right amt of secs?
        addParallel(new LiftMoveToBottomCommand());
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(0));
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand(),2);
        addSequential(new DrivetrainMoveInchesEncoderCommand(20, 0.1));
        addSequential(new QuisitorCloseCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(20, -0.1));
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(90));
        addSequential(new LiftMoveToHeightCommand(10));
        addSequential(curveToCube, 5); 
        addSequential(new QuisitorDeacquireCommand());
    }
}
