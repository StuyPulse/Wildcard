package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SingleCubeSwitchThenStartScaleAutonChooserCommand extends ConditionalCommand {

    public SingleCubeSwitchThenStartScaleAutonChooserCommand() {
        super(new SingleCubeSwitchThenStartRightScaleAutonChooserCommand(), new SingleCubeSwitchThenStartLeftScaleAutonChooserCommand());
    }

    @Override
    public boolean condition() {
        return Robot.isScaleOnRight();
    }

    private static class SingleCubeSwitchThenStartLeftScaleAutonChooserCommand extends ConditionalCommand {
        public SingleCubeSwitchThenStartLeftScaleAutonChooserCommand() {
            super(new SingleCubeSwitchThenStartLeftScaleAutonCommand(true), new SingleCubeSwitchThenStartLeftScaleAutonCommand(false));
        }
        
        @Override
        public boolean condition() {
            return Robot.isSwitchOnRight();
        }
    }

    private static class SingleCubeSwitchThenStartRightScaleAutonChooserCommand extends ConditionalCommand {
        public SingleCubeSwitchThenStartRightScaleAutonChooserCommand() {
            super(new SingleCubeSwitchThenStartRightScaleAutonCommand(true), new SingleCubeSwitchThenStartRightScaleAutonCommand(false));
        }
        
        @Override
        public boolean condition() {
            return Robot.isSwitchOnRight();
        }
    }
}

