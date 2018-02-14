package org.usfirst.frc.team694.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BITCOINCommand extends CommandGroup {

    public BITCOINCommand() {
        addSequential(new LiftMoveToHeightCommand(0));
        addSequential(new FlapAndFlipUpCommand());
        addSequential(new GrabberCloseCommand());
        }
    
    public boolean isFinished() {
        return false;
   }
}