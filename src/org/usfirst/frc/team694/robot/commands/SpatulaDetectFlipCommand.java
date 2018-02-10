package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.util.IRSensor;

import edu.wpi.first.wpilibj.command.Command;
/**
 *This will flip the cube when the IR sensor detects that the cube is not upright, and leave it when it detects the cube is upright
 */
public class SpatulaDetectFlipCommand extends Command {

    public SpatulaDetectFlipCommand() {
        requires(Robot.spatula);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (IRSensor.isCubeDetected()) {
            Robot.spatula.flipUp();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }
}
