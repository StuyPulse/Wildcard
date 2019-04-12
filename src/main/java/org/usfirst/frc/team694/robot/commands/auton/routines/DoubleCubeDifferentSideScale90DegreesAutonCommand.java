package org.usfirst.frc.team694.robot.commands.auton.routines;
import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorAcquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorCloseCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorOpenCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainMoveInchesEncoderCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleCubeDifferentSideScale90DegreesAutonCommand extends CommandGroup {

    public DoubleCubeDifferentSideScale90DegreesAutonCommand(boolean isRight) {
        // THIS COMMAND DOES NOT HAVE ARCS IMPLEMENTED YET. IF YOU HAVE TIME PLEASE DO IT :D
        // This command runs well with your other member of your alliance,
        // knowing Same Side Scale (90 degrees Null) Auton. Pictures can be found in Slack and Google Drive.
        addSequential(new SingleCubeDifferentSideScale90DegreesAutonCommand(isRight));
        // Drive backwards, rotate 190 degrees to the right and acquire the cube.
        // We drive backwards first so when you rotate, you don't hit the platform zone!
        addSequential(new DrivetrainMoveInchesEncoderCommand(-1 * 53,0.8), 1.5);
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? 190 : -190), 1.5);
        addSequential(new QuisitorOpenCommand());
        addParallel(new QuisitorAcquireCommand());
        addSequential(new DrivetrainMoveInchesEncoderCommand(30,0.8),1);
        addSequential(new QuisitorCloseCommand());
        // Rotate to the left 190 degrees to face the scale again, move forward 83 inches and deacquire.
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -190 : 190), 1.5);
        addParallel(new DrivetrainMoveInchesEncoderCommand(83,0.8),2);
        addSequential(new LiftMoveToHeightCommand(83));
        addSequential(new QuisitorDeacquireCommand(),1.5);
        addSequential(new LiftMoveToBottomCommand());
        
        
    }
}
