package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.util.IRSensor;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class BITCOINCheckCommand extends InstantCommand {
    public Command BITCOINCommand;
    public BITCOINCheckCommand() {
        super();
        this.BITCOINCommand = new BITCOINCommand();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (Robot.isBITCOINAutomation) {
            if (IRSensor.isCubeDetected()) {
                BITCOINCommand.start();
            }
        }
    }
}
