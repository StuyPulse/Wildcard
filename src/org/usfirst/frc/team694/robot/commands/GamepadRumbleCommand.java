package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.util.IRSensor;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GamepadRumbleCommand extends Command {
    
    public GamepadRumbleCommand() {
        if (IRSensor.isCubeDetected()) {
            Robot.oi.operatorGamepad.setRumble(GenericHID.RumbleType.kLeftRumble, 0.5);
        }
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
