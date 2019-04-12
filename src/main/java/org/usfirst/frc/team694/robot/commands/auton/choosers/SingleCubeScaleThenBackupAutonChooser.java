package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.SingleCubeScaleThenBackupAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SingleCubeScaleThenBackupAutonChooser extends ConditionalCommand {

    public SingleCubeScaleThenBackupAutonChooser() {
        super(new SingleCubeScaleThenBackupAutonCommand(true), new SingleCubeScaleThenBackupAutonCommand(false));
    }
    
    @Override
    public boolean condition() {
        return Robot.isRobotOnSameSideScale();
    }
}
