package org.usfirst.frc.team694.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BITCOINCommand extends CommandGroup {

    // Once we get the cube in the grabber, 
    private static final double SECONDS_TO_MOVE_CUBE_UP_AFTER_GRABBING = 0.6;

    public BITCOINCommand() {
        // Get the cube in the grabber
        addSequential(new LiftMoveToHeightCommand(0));
        addSequential(new FlapAndFlipUpCommand());
        addSequential(new GrabberCloseCommand());

        // Move the cube up a little to get it out of the spatula
        addSequential(new SpatulaDeacquireCommand(), SECONDS_TO_MOVE_CUBE_UP_AFTER_GRABBING);
        addParallel(new LiftMoveSpeedCommand(-0.7), SECONDS_TO_MOVE_CUBE_UP_AFTER_GRABBING);
        }
    
    public boolean isFinished() {
        return false;
   }
}