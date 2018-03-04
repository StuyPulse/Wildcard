package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SideSwitchAutonChooserCommand extends ConditionalCommand {

    private static final double SPATULA_DEACQUIRE_TIME = 1.5;

    public SideSwitchAutonChooserCommand() {
        super(new RightSideSwitchAutonCommand(), new LeftSideSwitchAutonCommand()); 
    }

    @Override
    protected boolean condition() {
        return Robot.isRobotAndSwitchOnSameSide();
    }

    // To be used in the two switch commands: Runs the spatula deacquire for a period of time
    static class SpatulaDeacquireTimeCommand extends CommandGroup {
        public SpatulaDeacquireTimeCommand() {
            addSequential(new SpatulaDeacquireCommand(), SPATULA_DEACQUIRE_TIME);
        }
    }

}