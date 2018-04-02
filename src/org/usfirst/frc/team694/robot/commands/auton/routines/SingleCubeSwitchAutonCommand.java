package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SingleCubeSwitchAutonCommand extends CommandGroup {
    
    public SingleCubeSwitchAutonCommand(boolean isRight) {
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(124);
        driveCommand.addSpeedChange(0.0, 0.6);
        driveCommand.addTurn(10.0, 90.0);
        driveCommand.addTurn(42.0, 0.0);
        addParallel(new LiftMoveToHeightCommand(30.0)); //TODO: Find a height for the lift to move to.
        addSequential(driveCommand, 7);
        addSequential(new QuisitorDeacquireCommand());
        
    }
}
