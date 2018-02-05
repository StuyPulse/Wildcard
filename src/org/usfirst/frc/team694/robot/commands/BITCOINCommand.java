package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BITCOINCommand extends CommandGroup {
    private boolean startBITCOINAutomation; //records initial automation mode
    private double height = 2;
    public BITCOINCommand() {
        addSequential(new AcquirerTongsTightenHoldCommand());
        addSequential(new CrabArmFlapOutCommand());
        addSequential(new AcquirerFlipUpCommand());
        addSequential(new LiftMoveToHeightCommand(0));
        addSequential(new LiftMoveToHeightCommand(height));
        addSequential(new GrabberCloseCommand());
        addSequential(new AcquirerTongsLoosenHoldCommand());
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