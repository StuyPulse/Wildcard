package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.NewSingleCubeSwitchThenStartLeftScaleAutonCommand;
import org.usfirst.frc.team694.robot.commands.auton.routines.NewSingleCubeSwitchThenStartRightScaleAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class NewSingleCubeSwitchThenStartScaleAutonChooserCommand extends ConditionalCommand {

    public NewSingleCubeSwitchThenStartScaleAutonChooserCommand() {
        super(new NewSingleCubeSwitchThenStartRightScaleAutonChooserCommand(), new NewSingleCubeSwitchThenStartLeftScaleAutonChooserCommand());
    }

    @Override
    public boolean condition() {
        return Robot.isScaleOnRight();
    }

    private static class NewSingleCubeSwitchThenStartLeftScaleAutonChooserCommand extends ConditionalCommand {
        public NewSingleCubeSwitchThenStartLeftScaleAutonChooserCommand() {
            super(new NewSingleCubeSwitchThenStartLeftScaleAutonCommand(true), new NewSingleCubeSwitchThenStartLeftScaleAutonCommand(false));
        }
        
        @Override
        public boolean condition() {
            return Robot.isSwitchOnRight();
        }
    }

    private static class NewSingleCubeSwitchThenStartRightScaleAutonChooserCommand extends ConditionalCommand {
        public NewSingleCubeSwitchThenStartRightScaleAutonChooserCommand() {
            super(new NewSingleCubeSwitchThenStartRightScaleAutonCommand(true), new NewSingleCubeSwitchThenStartRightScaleAutonCommand(false));
        }
        
        @Override
        public boolean condition() {
            return Robot.isSwitchOnRight();
        }
    }
}
