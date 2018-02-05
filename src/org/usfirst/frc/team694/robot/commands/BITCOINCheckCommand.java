package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class BITCOINCheckCommand extends ConditionalCommand { 
    public BITCOINCheckCommand() {

        super(new BITCOINAutoCommand(), new BITCOINManualCommand());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

    }

    @Override
    protected boolean condition() {
        return Robot.spatula.isBITCOINAutomation;

    }
}