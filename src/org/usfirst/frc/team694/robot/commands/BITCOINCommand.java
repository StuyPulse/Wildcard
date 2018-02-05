package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BITCOINCommand extends CommandGroup {
    public boolean startBITCOINAutomation; //records initial automation mode
    public double height = 2;
    public BITCOINCommand() {
        addSequential(new AcquirerTightenHoldCommand());
        addSequential(new AcquirerFlipUpCommand());
        addSequential(new LiftMaxDownCommand());
        //TODO when merge to branch with command, uncomment
        //addSequential(new LiftMoveUpHeightCommand(height));
        addSequential(new AcquirerLoosenHoldCommand());
        addSequential(new GrabberCloseCommand());
        }
    
    public void initialize() {
        startBITCOINAutomation = Robot.acquirer.isBITCOINAutomation;
    }
    public boolean isFinished() {
        if (startBITCOINAutomation && !(Robot.acquirer.isBITCOINAutomation)) {
            return true;
        } else {
            return super.isFinished();
        }
    }
}