package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class BITCOINCheckCommand extends ConditionalCommand { 
    public BITCOINCheckCommand() {

        super(new BITCOINAutoCommand(), new BITCOINManualCommand());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

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
        return Robot.spatula.isBITCOINAutomation;

    }
}