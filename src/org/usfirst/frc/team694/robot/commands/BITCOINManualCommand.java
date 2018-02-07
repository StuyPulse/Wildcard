package org.usfirst.frc.team694.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BITCOINManualCommand extends CommandGroup {
    private double height = 2;
    public BITCOINManualCommand() {
        addSequential(new SpatulaTongsTightenHoldCommand());
        addSequential(new CrabArmDeployCommand());
        addSequential(new SpatulaFlipUpCommand());
        addSequential(new LiftMoveToHeightCommand(0));
        addSequential(new LiftMoveToHeightCommand(height));
        addSequential(new GrabberCloseCommand());
        addSequential(new SpatulaTongsLoosenHoldCommand());
        }
    
    public boolean isFinished() {
        return false;
   }
}