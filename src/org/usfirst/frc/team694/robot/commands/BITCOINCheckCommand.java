package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class BITCOINCheckCommand extends ConditionalCommand { 
    public BITCOINCheckCommand() {
        super(new BITCOINCommand());
    }

    protected void initialize() {
    }
    
    protected void execute() {
    }
    
    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected boolean condition() {
        if ((Robot.spatula.isBITCOINAutomation && Robot.spatula.getIsCubeDetected()) 
                || (Robot.oi.operatorGamepad.getRawBottomButton())) {
            return true;
        } else {
            return false;
        }
    }
}