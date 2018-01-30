package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.BITCOINStage;
import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class SuperBITCOINCommand extends InstantCommand {

    public SuperBITCOINCommand() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.stage = BITCOINStage.getNextStage(Robot.stage);
        switch(Robot.stage) {
            case ONE:
                Scheduler.getInstance().add(new BITCOINPartOneCommand());
                break;
            case TWO:
                Scheduler.getInstance().add(new BITCOINPartTwoCommand());
                break;
            case THREE:
                Scheduler.getInstance().add(new BITCOINPartThreeCommand());
                break;
            default:
                //does nothing
                break;
        }
    }
}
