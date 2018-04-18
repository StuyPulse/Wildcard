package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.DoubleCubeDifferentSideScale90DegreesAutonCommand;
import org.usfirst.frc.team694.robot.commands.auton.routines.TripleCubeSameSideScaleCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class TripleCubeScaleAutonChooserCommand extends ConditionalCommand {

    public TripleCubeScaleAutonChooserCommand() {
        super(new TripleCubeSameSideScaleAutonChooserCommand(), new DoubleCubeDifferentSideScale90DegreesAutonChooserCommand());
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

    private static class DoubleCubeDifferentSideScale90DegreesAutonChooserCommand extends ConditionalCommand {

        public DoubleCubeDifferentSideScale90DegreesAutonChooserCommand() {
            super(new DoubleCubeDifferentSideScale90DegreesAutonCommand(true), new DoubleCubeDifferentSideScale90DegreesAutonCommand(false));
        }

        @Override
        public boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
    }
}
