package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivetrainLineSensorMultipleLinesCommand extends Command {

    public DrivetrainLineSensorMultipleLinesCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
    }
    
    static boolean done;

    // Called just before this Command runs the first time
    protected void initialize() {
        int i = 0;
        double timer = Timer.getFPGATimestamp();
        done = false;
        while (i <= SmartDashboard.getNumber("Lines To Cross", 3) && timer + 2 < Timer.getFPGATimestamp()) {
            if (Robot.drivetrain.isOnLine(42)) {
                i++;
            }
            timer = Timer.getFPGATimestamp();
        }
        done = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (done) {
            return true;
        }else {
            return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
