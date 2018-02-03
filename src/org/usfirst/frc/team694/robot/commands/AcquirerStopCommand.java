package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class AcquirerStopCommand extends InstantCommand {
    public AcquirerStopCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.acquirer);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.acquirer.stop();
    }

}
