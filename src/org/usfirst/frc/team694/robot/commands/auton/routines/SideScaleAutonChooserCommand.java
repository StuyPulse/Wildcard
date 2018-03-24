package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SideScaleAutonChooserCommand extends ConditionalCommand {

    public SideScaleAutonChooserCommand() {
        super(new SameSideScaleAutonChooserCommand(), new DifferentSideScaleAutonChooserCommand());
    }

    @Override
    protected boolean condition() {
        return Robot.isRobotAndScaleOnSameSide();
    }
    
    // Same Side Conditional for where the robot starts
    private static class SameSideScaleAutonChooserCommand extends ConditionalCommand {
        public SameSideScaleAutonChooserCommand() {
            super(new SameSideScaleAutonCommand(true), new SameSideScaleAutonCommand(false));
        }

        @Override
        protected boolean condition() {
            return Robot.isRobotStartingOnRight();
        }        
    }

    // Same Side Conditional for where the robot starts
    private static class DifferentSideScaleAutonChooserCommand extends ConditionalCommand {
        public DifferentSideScaleAutonChooserCommand() {
            super(new SimpleDifferentSideScaleAutonCommand(true), new SimpleDifferentSideScaleAutonCommand(false));
        }

        @Override
        protected boolean condition() {
            return Robot.isRobotStartingOnRight();
        }        
    }

}