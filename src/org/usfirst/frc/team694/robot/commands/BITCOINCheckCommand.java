package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BITCOINCheckCommand extends Command {
    public Command BITCOINCommand;
    
    public BITCOINCheckCommand() {
        this.BITCOINCommand = new BITCOINCommand();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    protected void execute() {
        if ((Robot.acquirer.isBITCOINAutomation && Robot.acquirer.getIsCubeDetected()) 
                || (Robot.oi.operatorGamepad.getRawBottomButton())) {
                BITCOINCommand.start();
            }
        }
    }

    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
}