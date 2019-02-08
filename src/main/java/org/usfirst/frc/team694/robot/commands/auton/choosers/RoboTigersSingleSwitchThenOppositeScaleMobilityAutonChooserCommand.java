package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.RoboTigersSingleSwitchThenOppositeScaleMobilityAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 * If we are alliance partners with a team that can do two cubes, like the RoboTigers, we'll run the double 
 * same side scale auton if we're on the same side as the scale. If not, we'll score the switch then turn towards our scale
 * while our alliance partners score two scale cubes.
 */
public class RoboTigersSingleSwitchThenOppositeScaleMobilityAutonChooserCommand extends ConditionalCommand {

    public RoboTigersSingleSwitchThenOppositeScaleMobilityAutonChooserCommand() {
        super(new DoubleCubeScaleAutonChooserCommand(), new RoboTigersSingleSwitchThenOppositeScaleMobilityAutonCommandChooser());
    }
    
    @Override
    public boolean condition() {
        return Robot.isRobotOnSameSideScale();
    }
    
    private static class RoboTigersSingleSwitchThenOppositeScaleMobilityAutonCommandChooser extends ConditionalCommand{
        public RoboTigersSingleSwitchThenOppositeScaleMobilityAutonCommandChooser() {
            super(new RoboTigersSingleSwitchThenOppositeScaleMobilityAutonCommand(true), new RoboTigersSingleSwitchThenOppositeScaleMobilityAutonCommand(false));
        }

        @Override
        public boolean condition() {
            return Robot.isRobotOnSameSideSwitch();
        }
    }
}
