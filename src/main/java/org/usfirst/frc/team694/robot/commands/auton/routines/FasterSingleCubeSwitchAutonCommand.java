package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FasterSingleCubeSwitchAutonCommand extends CommandGroup {

    public FasterSingleCubeSwitchAutonCommand(boolean isRight) {

            //TODO: Make sure that distance is far enough to avoid tech foul
            DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand((isRight? 118 : 119 - 3) + 8 + 5/*5 + 3*/ );

            driveCommand.addTurn(isRight? 5.0 : 2.5, isRight ? 90.0 : -90.0); // Originally was 10 inches
            driveCommand.addTurn(isRight ? 68.0 : 68, 0.0);

//            addSequential(new LiftMoveToHeightCommand(30.0)); //TODO: Find a height for the lift to move to.
//            addSequential(new WaitCommand(0.5));
            //TODO: Change distance at which the lift is raised
            addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30.0), 0));
            addSequential(driveCommand, 2.75 /*3.5*/);
            addSequential(new QuisitorDeacquireCommand(), 0.5);
            //faster one: addParallel(new QuisitorDeacquireCommand(), 0.5);
            addSequential(new DrivetrainMoveInchesEncoderCommand(10 + 5, -1)); // -0.45
    }
}
