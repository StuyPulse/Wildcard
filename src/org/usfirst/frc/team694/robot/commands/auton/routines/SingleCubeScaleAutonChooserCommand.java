package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SingleCubeScaleAutonChooserCommand extends ConditionalCommand {

    public SingleCubeScaleAutonChooserCommand() {
        super(new SingleCubeSameSideScaleAutonChooserCommand(), new SingleCubeDifferentSideScaleAutonChooserCommand());
    }

    @Override
    public boolean condition() {
        return Robot.isRobotOnSameSideScale();
    }

    private static class SingleCubeSameSideScaleAutonChooserCommand extends ConditionalCommand {
        public SingleCubeSameSideScaleAutonChooserCommand() {
            super(new SingleCubeSameSideScaleAutonCommand(true), new SingleCubeSameSideScaleAutonCommand(false));
        }

        @Override
        public boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
    }

    private static class SingleCubeDifferentSideScaleAutonChooserCommand extends ConditionalCommand {
        public SingleCubeDifferentSideScaleAutonChooserCommand() {
            super(new SingleCubeDifferentSideScaleAutonCommand(true), new SingleCubeDifferentSideScaleAutonCommand(false));
        }

        @Override
        public boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
    }
}
