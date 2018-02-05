package org.usfirst.frc.team694.robot.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 */    
public class BITCOINManualCommand extends CommandGroup {
    private double height = 2;
    public BITCOINManualCommand() {
        addSequential(new AcquirerTongsTightenHoldCommand());
        addSequential(new CrabArmFlapOutCommand());
        addSequential(new AcquirerFlipUpCommand());
        addSequential(new LiftMoveToHeightCommand(0));
        addSequential(new LiftMoveToHeightCommand(height));
        addSequential(new GrabberCloseCommand());
        addSequential(new AcquirerTongsLoosenHoldCommand());
        }
    
    public boolean isFinished() {
        return false;
   }
}