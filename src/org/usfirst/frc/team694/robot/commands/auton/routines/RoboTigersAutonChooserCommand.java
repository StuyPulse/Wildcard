package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class RoboTigersAutonChooserCommand extends ConditionalCommand {

    public RoboTigersAutonChooserCommand() {
        super(new DoubleCubeScaleAutonChooserCommand(), new RoboTigersOppositeSideScaleAutonChooser());
        
    }
    
    @Override
    public boolean condition() {
        return Robot.isRobotOnSameSideScale();
    }
    
    private static class RoboTigersOppositeSideScaleAutonChooser extends ConditionalCommand {
        public RoboTigersOppositeSideScaleAutonChooser() {
            super(new RoboTigersOppositeSideScaleAutonCommand(true), new RoboTigersOppositeSideScaleAutonCommand(false));
        }
        
        @Override
        public boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
    }
}
