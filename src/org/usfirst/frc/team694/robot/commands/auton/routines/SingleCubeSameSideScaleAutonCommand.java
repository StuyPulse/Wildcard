package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.commands.LiftMoveToBottomCommand;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorMoveSpeedCommand;
import org.usfirst.frc.team694.robot.commands.auton.AutonCommandGroup;
import org.usfirst.frc.team694.robot.commands.auton.ConditionalDistanceEncodersCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightWithRampingCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainDriveCurveCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class SingleCubeSameSideScaleAutonCommand extends AutonCommandGroup {
    //private static final double TOTAL_DISTANCE = 286; //TODO: Not sure about this distance
    private static final double DISTANCE_TOTAL = 130 + 116 + 40;//296 - 4;
    public SingleCubeSameSideScaleAutonCommand(boolean isRight) {
        
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(DISTANCE_TOTAL);
        driveCommand.addSpeedChange(0, 0.75);
        driveCommand.addTurn(130 - 35, isRight ? -(45/2 - 5 - 5) : (45/2 - 5 - 5));
//      driveCommand.addTurn(130 + 116, isRight ? 5 : -5);

        // Curve to the scale + ready to score
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(86), 15));
        addSequential(driveCommand, 3.3);

        // Spit out cube
        addParallel(new QuisitorMoveSpeedCommand(-0.7), 0.5);
//        addSequential(new PrintCommand("[SingleCubeSameSideScale] isRight? " + isRight));
//
//        // Move to Scale
//
//        // If browning out while turning+lifting, comment this line
//        addParallel(new LiftMoveToHeightCommand(5.0));
//        addSequential(new DriveStraightWithRampingCommand(261 + 12 + 6 /*+ 7*/), 3.5 - .5);
//        // If browning out while turning+lifting, uncomment this line
//        // addParallel(new LiftMoveToHeightCommand(86.0));
//
//        // This is against Joe Ricci/Mr. Blay's reasoning, but I think this will work better
//        // because it plants our first cube further from the center (which would be a problem)
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight ? -(60-15) : (60-15)), 1.5 /* - .5  + .5*/);
//
//        addSequential(new LiftMoveToHeightCommand(86));
//        addSequential(new QuisitorDeacquireCommand(), 0.5);
//        //addSequential(new DrivetrainMoveInchesEncoderCommand(5, .4));
//        addSequential(new LiftMoveToBottomCommand());

        /*
        DrivetrainDriveCurveCommand driveCommand = new DrivetrainDriveCurveCommand(TOTAL_DISTANCE);
        driveCommand.addSpeedChange(0, 0.8);
        driveCommand.addTurn(261 - 12, isRight? -45.0 : 45.0);
        //TODO: Make sure this logic is correct
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(82.0), 168));
        addSequential(driveCommand, 3.5);
        */

//        // If browning out while turning+lifting, comment this line
//        addParallel(new LiftMoveToHeightCommand(5.0));
//        addSequential(new DriveStraightWithRampingCommand(261 + 12 + 6), 3.5);
//        // If browning out while turning+lifting, uncomment this line
////        addParallel(new LiftMoveToHeightCommand(86.0));
//        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(isRight? -60 : 60), 1.5 /* + .5*/);
//        addSequential(new LiftMoveToHeightCommand(86));
//        addSequential(new QuisitorDeacquireCommand(), 0.5);
//        addSequential(new LiftMoveToBottomCommand());

    }
}
