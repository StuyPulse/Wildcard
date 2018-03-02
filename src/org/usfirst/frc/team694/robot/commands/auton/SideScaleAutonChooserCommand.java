package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SideScaleAutonChooserCommand extends ConditionalCommand {

    public SideScaleAutonChooserCommand() {
        super(new SameSideScaleAutonCommand(), new SimpleDifferentSideScaleAutonCommand());
    }

    @Override
    protected boolean condition() {
        return Robot.isRobotAndScaleOnSameSide();
    }
}