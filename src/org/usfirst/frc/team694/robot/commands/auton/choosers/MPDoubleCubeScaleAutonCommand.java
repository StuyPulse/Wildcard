package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.MPDoubleCubeDifferentSideScaleAutonCommand;
import org.usfirst.frc.team694.robot.commands.auton.routines.TripleCubeSameSideScaleCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class MPDoubleCubeScaleAutonCommand extends ConditionalCommand {

    public MPDoubleCubeScaleAutonCommand() {
        super(new TripleCubeSameSideScaleAutonChooserCommand(), new MPDoubleCubeScaleAutonDifferentSideChooser());
    }

    @Override
    public boolean condition() {
        return Robot.isRobotOnSameSideScale();
    }

    private static class MPDoubleCubeScaleAutonDifferentSideChooser extends ConditionalCommand {
        public MPDoubleCubeScaleAutonDifferentSideChooser() {
            super(new MPDoubleCubeDifferentSideScaleAutonCommand(true), new MPDoubleCubeDifferentSideScaleAutonCommand(false));
        }

        @Override
        public boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
    }
    
    private static class TripleCubeSameSideScaleAutonChooserCommand extends ConditionalCommand {

        public TripleCubeSameSideScaleAutonChooserCommand() {
            // WITH CURVING
            super(new TripleCubeSameSideScaleCommand(true), new TripleCubeSameSideScaleCommand(false));
            //super(new TripleCubeScaleAutonCommand(true), new TripleCubeScaleAutonCommand(false));
        }

        @Override
        public boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
    }
}