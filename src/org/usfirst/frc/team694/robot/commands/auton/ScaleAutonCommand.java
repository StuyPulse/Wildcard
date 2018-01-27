package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;
import org.usfirst.frc.team694.robot.commands.LiftMaxUpCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;


public class ScaleAutonCommand extends CommandGroup {
    public ScaleAutonCommand() {
        addSequential(new DrivetrainMoveToLineCommand(speed));
        if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R') {
            addSequential(new DrivetrainMoveInchesEncoderCommand(FirstLineToSpaceBetweenScaleAndSwitch, speed));
            addSequential(new DrivetrainRotateDegreesPIDCommand(90));
            addSequential(new DrivetrainMoveToLineCommand(speed));
            addParallel(new LiftMaxUpCommand());
            addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
            addSequential(new DrivetrainMoveToLineCommand(speed));
            addSequential(new GrabberOpenCommand());
        }
        else {
            /*addSequential(new DrivetrainMoveToLineCommand(1, maxDistance, speed));
            addSequential(new DrivetrainMoveInchesEncoderCommand(halfNullDistance, speed));
            addParallel(new LiftMaxUpCommand());
            addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
            addSequential(new DrivetrainMoveInchesEncoderCommand(halfNullToScale, speed));
            addSequential(new GrabberOpenCommand());
            */
            addSequential(new DrivetrainRotateDegreesPIDCommand(degrees));
            addSequential(new DrivetrainMoveInchesEncoderCommand(hypothenuse, speed));
            addParallel(new LiftMaxUpCommand());
            addSequential(new DrivetrainRotateDegreesPIDCommand(-degrees));
            addSequential(new GrabberOpenCommand());
        }
    }
}
