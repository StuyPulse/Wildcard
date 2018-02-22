package org.usfirst.frc.team694.robot.commands.auton;

/**
 *  Changes the target angle of a ramp command at a certain distance
 */
public class DrivetrainRampingSetTargetAngleAtDistanceCommand extends ConditionalDistanceEncodersCommand {

    public DrivetrainRampingSetTargetAngleAtDistanceCommand(
            DriveStraightWithRampingCommand rampCommand, 
            double distance,
            double angle
            ) {

        super(new DrivetrainRampingSetTargetAngleCommand(rampCommand, angle), distance);
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        System.out.println("[SetTargetAngle]");
    }
}
