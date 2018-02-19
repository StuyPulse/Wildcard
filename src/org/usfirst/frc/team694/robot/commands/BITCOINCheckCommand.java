package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class BITCOINCheckCommand extends ConditionalCommand { 
    
    public BITCOINCheckCommand() {  
        super(new BITCOINCubeDetectedCommand(), new CrabArrowAcquireCommand());
    }

    @Override
    protected boolean condition() {
        return Robot.spatula.isBITCOINAutomation;
        // if true, will run BITCOINAutoCommand, which includes the acquirer limit switch
        // if false, will run the BITCOINManualCommand, in which the acquirer has already stopped
    }
}