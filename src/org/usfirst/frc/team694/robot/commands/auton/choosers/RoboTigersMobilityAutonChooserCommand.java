package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.RoboTigersOppositeSideScaleMobilityAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class RoboTigersMobilityAutonChooserCommand extends ConditionalCommand {

    public RoboTigersMobilityAutonChooserCommand() {
        super(new DoubleCubeScaleAutonChooserCommand(), new RoboTigersOppositeSideScaleMobilityAutonChooser());

    }

    @Override
    public boolean condition() {
        return Robot.isRobotOnSameSideScale();
    }

    private static class RoboTigersOppositeSideScaleMobilityAutonChooser extends ConditionalCommand {
        public RoboTigersOppositeSideScaleMobilityAutonChooser() {
            super(new RoboTigersOppositeSideScaleMobilityAutonCommand(true), new RoboTigersOppositeSideScaleMobilityAutonCommand(false));
        }

        @Override
        public boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
    }
}
