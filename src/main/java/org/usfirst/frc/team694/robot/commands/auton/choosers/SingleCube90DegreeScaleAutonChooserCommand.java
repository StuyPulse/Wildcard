package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.SameSideScaleNinetyNullAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SingleCube90DegreeScaleAutonChooserCommand extends ConditionalCommand {

    public SingleCube90DegreeScaleAutonChooserCommand() {
        super(new SingleCube90DegreeSameSideScaleAutonChooserCommand(),new DoubleCubeScaleAutonChooserCommand());
    }

    @Override
    protected boolean condition() {
        return Robot.isRobotOnSameSideScale();
    }
    
    private static class SingleCube90DegreeSameSideScaleAutonChooserCommand extends ConditionalCommand {
        
        public SingleCube90DegreeSameSideScaleAutonChooserCommand() {
            super(new SameSideScaleNinetyNullAutonCommand(true),new SameSideScaleNinetyNullAutonCommand(false));
        }
        
        @Override
        protected boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
    }
}
