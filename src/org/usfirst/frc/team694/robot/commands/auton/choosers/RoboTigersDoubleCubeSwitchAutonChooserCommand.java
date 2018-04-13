package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.RoboTigersDoubleCubeSwitchAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class RoboTigersDoubleCubeSwitchAutonChooserCommand extends ConditionalCommand {

    public RoboTigersDoubleCubeSwitchAutonChooserCommand() {
        super(new DoubleCubeScaleAutonChooserCommand(), new RoboTigersRealDoubleCubeSwitchAutonChooser());
    }

    @Override
    public boolean condition() {
        return Robot.isRobotOnSameSideScale();
    }

    private static class RoboTigersRealDoubleCubeSwitchAutonChooser extends ConditionalCommand {
        public RoboTigersRealDoubleCubeSwitchAutonChooser() {
            super(new RoboTigersRealDoubleCubeSwitchRightStartAutonChooserCommand(), new RoboTigersRealDoubleCubeSwitchLeftStartAutonChooserCommand());
        }

        @Override
        public boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
        
        private static class RoboTigersRealDoubleCubeSwitchRightStartAutonChooserCommand extends ConditionalCommand {
            public RoboTigersRealDoubleCubeSwitchRightStartAutonChooserCommand() {
                super(new RoboTigersDoubleCubeSwitchAutonCommand(true, true), new RoboTigersDoubleCubeSwitchAutonCommand(true, false));
            }
            @Override
            public boolean condition() {
                return Robot.isRobotOnSameSideSwitch();
            }
        }

        private static class RoboTigersRealDoubleCubeSwitchLeftStartAutonChooserCommand extends ConditionalCommand {
            public RoboTigersRealDoubleCubeSwitchLeftStartAutonChooserCommand() {
                super(new RoboTigersDoubleCubeSwitchAutonCommand(false, true), new RoboTigersDoubleCubeSwitchAutonCommand(false, false));
            }
            @Override
            public boolean condition() {
                return Robot.isRobotOnSameSideSwitch();
            }
        }

    }
}
