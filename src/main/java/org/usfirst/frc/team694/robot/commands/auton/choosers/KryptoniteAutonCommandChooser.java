package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.KryptoniteAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class KryptoniteAutonCommandChooser extends ConditionalCommand {

    public KryptoniteAutonCommandChooser() {
        super(new KryptoniteAutonChooser());
    }

    @Override
    protected boolean condition() {
        return Robot.isRobotOnSameSideSwitch();
    }
    
    public static class KryptoniteAutonChooser extends ConditionalCommand {
        public KryptoniteAutonChooser() {
            super(new KryptoniteAutonCommand(true), new KryptoniteAutonCommand(false));
        }
        
        @Override
        public boolean condition() {
            return Robot.isRobotStartingOnRight();
        }
    }
}
      