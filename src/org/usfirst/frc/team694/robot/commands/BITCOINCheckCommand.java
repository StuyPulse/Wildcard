package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class BITCOINCheckCommand extends ConditionalCommand { 
    public BITCOINCheckCommand() {
        super(new BITCOINCommand());
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