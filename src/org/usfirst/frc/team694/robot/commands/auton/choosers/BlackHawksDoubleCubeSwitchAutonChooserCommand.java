package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.BlackHawksDoubleCubeSwitchAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class BlackHawksDoubleCubeSwitchAutonChooserCommand extends ConditionalCommand {

    public BlackHawksDoubleCubeSwitchAutonChooserCommand() {
        super(new DoubleCubeScaleAutonChooserCommand(), new BlackHawksRealDoubleCubeSwitchAutonChooser());
    }

    @Override
    public boolean condition() {
        return Robot.isRobotOnSameSideScale();
    }

    private static class BlackHawksRealDoubleCubeSwitchAutonChooser extends ConditionalCommand {
        public BlackHawksRealDoubleCubeSwitchAutonChooser() {
            super(new BlackHawksRealDoubleCubeSwitchRightStartAutonChooserCommand(), new BlackHawksRealDoubleCubeSwitchLeftStartAutonChooserCommand());
        }

        @Override
        public boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
        
        private static class BlackHawksRealDoubleCubeSwitchRightStartAutonChooserCommand extends ConditionalCommand {
            public BlackHawksRealDoubleCubeSwitchRightStartAutonChooserCommand() {
                super(new BlackHawksDoubleCubeSwitchAutonCommand(true, true), new BlackHawksDoubleCubeSwitchAutonCommand(true, false));
            }
            @Override
            public boolean condition() {
                return Robot.isRobotOnSameSideSwitch();
            }
        }

        private static class BlackHawksRealDoubleCubeSwitchLeftStartAutonChooserCommand extends ConditionalCommand {
            public BlackHawksRealDoubleCubeSwitchLeftStartAutonChooserCommand() {
                super(new BlackHawksDoubleCubeSwitchAutonCommand(false, true), new BlackHawksDoubleCubeSwitchAutonCommand(false, false));
            }
            @Override
            public boolean condition() {
                return Robot.isRobotOnSameSideSwitch();
            }
        }

    }
}
