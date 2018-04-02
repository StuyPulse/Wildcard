package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand.RampMode;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class SingleCubeSwitchAutonCommand extends CommandGroup {
    
    public SingleCubeSwitchAutonCommand() {
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(118);
        driveCommand.addSpeedChange(0, 0.6);
//        driveCommand.addSpeedChange(0.0, 0.4);
//        driveCommand.addSpeedChange(0.0 + 10, 0.6);
        driveCommand.addTurn(5.0, 90.0); // Originally was 10 inches
        driveCommand.addTurn(42.0 + 20 + 6, 0.0);

        addSequential(new LiftMoveToHeightCommand(30.0)); //TODO: Find a height for the lift to move to.
        addSequential(new WaitCommand(0.5));
        addSequential(driveCommand, 7);
        addSequential(new QuisitorDeacquireCommand());
        
    }
}
