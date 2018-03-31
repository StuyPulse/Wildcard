package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SingleCubeScaleAutonChooserCommand extends ConditionalCommand {
    private static boolean isRobotStartingOnRight;

    public SingleCubeScaleAutonChooserCommand() {
        super(new SingleCubeSameSideScaleAutonCommand(isRobotStartingOnRight), new SingleCubeDifferentSideScaleAutonCommand(isRobotStartingOnRight));
    }

    @Override
    public boolean condition() {
        isRobotStartingOnRight = Robot.isRobotStartingOnRight();
        return Robot.isRobotOnSameSideScale();
    }
}
