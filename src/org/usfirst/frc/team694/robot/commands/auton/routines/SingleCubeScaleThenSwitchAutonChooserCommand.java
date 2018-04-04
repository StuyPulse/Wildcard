package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SingleCubeScaleThenSwitchAutonChooserCommand extends ConditionalCommand {

    public SingleCubeScaleThenSwitchAutonChooserCommand() {
        super(new SingleCubeScaleThenSwitchAutonChooser(), new SingleCubeScaleAutonChooserCommand());
    }

    @Override
    public boolean condition() {
        return Robot.isSwitchOnSameSideScale();
    }
    
    private static class SingleCubeScaleThenSwitchAutonChooser extends ConditionalCommand {
        public SingleCubeScaleThenSwitchAutonChooser() {
            super(new SingleCubeScaleThenSwitchAutonCommand(true), new SingleCubeScaleThenSwitchAutonCommand(false));
        }

        @Override
        public boolean condition() {
            return Robot.isScaleOnRight();
        }
    }
}
