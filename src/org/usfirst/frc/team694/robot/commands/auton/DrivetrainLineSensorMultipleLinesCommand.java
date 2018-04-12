package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DrivetrainLineSensorMultipleLinesCommand extends CommandGroup {

    private static final double AFTER_LINE_WAIT_DELAY = 0.5;

    public DrivetrainLineSensorMultipleLinesCommand(int lineNumber, double setDistance) {
        for (int i = 0; i < lineNumber - 1; i++) {
            addSequential(new DrivetrainLineSensorEmptyCommand());
            addSequential(new WaitCommand(AFTER_LINE_WAIT_DELAY));
        }
        addSequential(new LineSensorRecalibrationCommand(setDistance));
    }

    private class DrivetrainLineSensorEmptyCommand extends Command {
        @Override
        protected boolean isFinished() {
            return Robot.drivetrain.isOnLine();
        }
    }
}
    