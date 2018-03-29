package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *   This is how we circumvent auton commands running
 *   at the same times as default commands
 */

// FIXME: Figure out a cleaner way to handle this issue
// (Command group gets interrupted but not canceled, and so
//   the scheduler thinks it's ok to run the Drive System Command
//   at the same time, since it's not in the scheduler)

public abstract class DefaultCommand extends Command {

    @Override
    protected void initialize() {
        // Don't run during auton
        if (Robot.getInstance().isAutonomous()) {
            cancel();
        }
    }

    @Override
    protected void execute() {
        if (!Robot.getInstance().isAutonomous()) {
            defaultExecute();
        }
    }
    
    protected abstract void defaultExecute();

}
