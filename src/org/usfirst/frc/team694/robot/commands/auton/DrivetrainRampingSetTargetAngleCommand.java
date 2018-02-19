package org.usfirst.frc.team694.robot.commands.auton;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *   Sets the target angle of a ramp command
 */
public class DrivetrainRampingSetTargetAngleCommand extends InstantCommand {

    private double targetAngle;
    private DriveStraightWithRampingCommand rampCommand;

    public DrivetrainRampingSetTargetAngleCommand(DriveStraightWithRampingCommand rampCommand, double targetAngle) {
        this.rampCommand = rampCommand;
        this.targetAngle = targetAngle;
    }

    @Override
    protected void initialize() {
        rampCommand.setTargetAngle(targetAngle);
    }
}
