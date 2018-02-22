package org.usfirst.frc.team694.robot.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team694.robot.Robot;

/**
 *
 */
public class DrivetrainLineSensorMultipleLinesCommand extends CommandGroup {
    
    public DrivetrainLineSensorMultipleLinesCommand(int amountOfLinesToCross,double offset) {
        for(int x = 1; x < amountOfLinesToCross; x++) {
            addSequential(new DrivetrainLineSensorDoNothingCommand());
        }
        addSequential(new DrivetrainLineSensorCommand(offset)); //TODO: find the distance needed
    }

        private class DrivetrainLineSensorDoNothingCommand extends Command{
            
            public DrivetrainLineSensorDoNothingCommand() { //TODO: create a better variable name, I was brain dead doing this
                requires(Robot.drivetrain);
            }
    
            @Override
            protected boolean isFinished() {
                return Robot.drivetrain.isOnLine();
            }
        }
}
    