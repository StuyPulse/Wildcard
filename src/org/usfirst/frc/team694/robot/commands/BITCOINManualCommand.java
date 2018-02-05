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
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
}
