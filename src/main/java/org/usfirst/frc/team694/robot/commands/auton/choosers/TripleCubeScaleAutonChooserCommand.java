package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.SingleCubeDifferentSideScaleAutonCommand;
import org.usfirst.frc.team694.robot.commands.auton.routines.TripleCubeSameSideScaleCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class TripleCubeScaleAutonChooserCommand extends ConditionalCommand {

    public TripleCubeScaleAutonChooserCommand() {
        super(new TripleCubeSameSideScaleAutonChooserCommand(), new DoubleCubeDifferentSideScaleAutonChooserCommand());
        //super(new TripleCubeScaleAutonCommand(true), new TripleCubeScaleAutonCommand(false));
    }

    @Override
    public boolean condition() {
        return Robot.isRobotOnSameSideScale();
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

    private static class DoubleCubeDifferentSideScaleAutonChooserCommand extends ConditionalCommand {

        public DoubleCubeDifferentSideScaleAutonChooserCommand() {
            super(new SingleCubeDifferentSideScaleAutonCommand(true), new SingleCubeDifferentSideScaleAutonCommand(false));
//            super(new DoubleCubeDifferentSideScale90DegreesAutonCommand(true), new DoubleCubeDifferentSideScale90DegreesAutonCommand(false));
        }

        @Override
        public boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
    }
}
