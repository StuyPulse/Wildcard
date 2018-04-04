package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SingleCubeScaleThenSwitchAutonChooserCommand extends ConditionalCommand {

    public SingleCubeScaleThenSwitchAutonChooserCommand() {
        super(new SingleCubeScaleThenSwitchAutonChooser(), new SingleCubeScaleAutonChooserCommand());
    }

    @Override
    public boolean condition() {
        return Robot.isRobotSwitchScaleOnSameSide();
        //If the robot, scale, and switch are on the same side, it will run Scale then Switch.
        //Otherwise, it will run the Single Cube Scale, different side or same side depending on position.
        //TODO: Change to double cube chooser if it is consistent.
    }
    
    private static class SingleCubeScaleThenSwitchAutonChooser extends ConditionalCommand {
        public SingleCubeScaleThenSwitchAutonChooser() {
            super(new SingleCubeScaleThenSwitchAutonCommand(true), new SingleCubeScaleThenSwitchAutonCommand(false));
        }

        @Override
        public boolean condition() {
            return Robot.isScaleOnRight();
            //Determines whether the robot will be playing on the right side or left side of the field.
        }
    }
}
