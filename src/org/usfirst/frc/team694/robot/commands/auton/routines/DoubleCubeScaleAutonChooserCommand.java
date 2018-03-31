package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class DoubleCubeScaleAutonChooserCommand extends ConditionalCommand {

    public DoubleCubeScaleAutonChooserCommand() {
        super(new DoubleCubeScaleAutonCommand(true), new DoubleCubeScaleAutonCommand(false));
    }

    @Override
    public boolean condition() {
        return Robot.isRobotStartingOnRight();
    }
}