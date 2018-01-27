package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMaxUpCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;


public class DifferentSideScaleAutonCommand extends CommandGroup {
    public DifferentSideScaleAutonCommand() {
        addSequential(new DrivetrainMoveToLineCommand(speed));
        addSequential(new DrivetrainMoveInchesEncoderCommand(FirstLineToSpaceBetweenScaleAndSwitch, speed));
        addSequential(new DrivetrainRotateDegreesPIDCommand(90));
        addSequential(new DrivetrainMoveToLineCommand(speed));
        addParallel(new LiftMaxUpCommand());
        addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
        addSequential(new DrivetrainMoveToLineCommand(speed));
        addSequential(new GrabberOpenCommand());
      
            /*addSequential(new DrivetrainMoveToLineCommand(1, maxDistance, speed));
            addSequential(new DrivetrainMoveInchesEncoderCommand(halfNullDistance, speed));
            addParallel(new LiftMaxUpCommand());
            addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
            addSequential(new DrivetrainMoveInchesEncoderCommand(halfNullToScale, speed));
            addSequential(new GrabberOpenCommand());
            */
    }
}
