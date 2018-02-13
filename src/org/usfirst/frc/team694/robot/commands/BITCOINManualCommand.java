package org.usfirst.frc.team694.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BITCOINManualCommand extends CommandGroup {
    private double height = 2;
    public BITCOINManualCommand() {
        addSequential(new LiftMoveToHeightCommand(0));
        addSequential(new FlapAndFlipUpCommand());
        addSequential(new LiftMoveToHeightCommand(height));
        addSequential(new GrabberCloseCommand());
        }
    
    public boolean isFinished() {
        return false;
   }
}