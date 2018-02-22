package org.usfirst.frc.team694.robot.commands.auton;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class DrivetrainRampingSetSpeedScaleAtDistanceCommand extends ConditionalDistanceEncodersCommand {

    public DrivetrainRampingSetSpeedScaleAtDistanceCommand(
            DriveStraightWithRampingCommand rampCommand, 
            double distance, 
            double factor) {
        super(new DrivetrainRampingSetSpeedScaleCommand(rampCommand, factor), distance);
    }

    private static class DrivetrainRampingSetSpeedScaleCommand extends InstantCommand {

        private DriveStraightWithRampingCommand rampCommand;
        private double speedScaleFactor;

        public DrivetrainRampingSetSpeedScaleCommand(DriveStraightWithRampingCommand rampCommand, double speedScaleFactor) {
            this.rampCommand = rampCommand;
            this.speedScaleFactor = speedScaleFactor;
        }

        @Override
        protected void initialize() {
            rampCommand.setSpeedScale(speedScaleFactor);
        }
    }
}
