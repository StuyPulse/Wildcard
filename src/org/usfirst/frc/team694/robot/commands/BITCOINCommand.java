package org.usfirst.frc.team694.robot.commands;
import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BITCOINCommand extends CommandGroup {

    // Once we get the cube in the grabber, 
    private static final double SECONDS_TO_MOVE_CUBE_UP_AFTER_GRABBING = 0.6;

    public BITCOINCommand() {
        // Get the cube in the grabber
        addSequential(new LiftMoveToBottomCommand());
        addSequential(new GrabberOpenCommand());

        
        addParallel(new SpatulaAcquireCommand(), RobotMap.PRE_FLIP_WAIT_TIME + RobotMap.POST_FLIP_WAIT_TIME);
        addSequential(new FlapAndFlipUpCommand());

        addSequential(new WaitCommand(0.25));

        addSequential(new GrabberCloseCommand());

        //addSequential(new WaitCommand(0.5));

        // Move the cube up a little to get it out of the spatula (not supposed to be used anymore)
        //addParallel(new SpatulaMoveSpeedCommand(-0.4 / 5.0), SECONDS_TO_MOVE_CUBE_UP_AFTER_GRABBING);
        //addSequential(new LiftMoveSpeedCommand(0.4), SECONDS_TO_MOVE_CUBE_UP_AFTER_GRABBING);
    }
    
    public boolean isFinished() {
        return false;
   }
}