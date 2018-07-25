package org.usfirst.frc.team694.robot.commands.auton;

public class DriveStraightRampUpOnlyCommand extends DriveStraightWithRampingCommand {

    public DriveStraightRampUpOnlyCommand(double targetDistance) {
        super(targetDistance);
    }

    @Override
    protected void initialize() {
        super.initialize();
        // 100% bang bang controller
        speedPIDController.setPID(1,0,0);
    }

    @Override
    protected boolean isFinished() {
        return getDistanceFromTarget() < 0;
    }

}
