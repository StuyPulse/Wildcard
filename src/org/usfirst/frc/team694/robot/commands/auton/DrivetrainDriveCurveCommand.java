package org.usfirst.frc.team694.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *    A command that lets us trick DriveStraightPID to turn and change speed mid trajectory (wildcard motion profiling)
 */

public class DrivetrainDriveCurveCommand extends CommandGroup {

    public enum RampMode {
        RAMP_FULL,
        NO_RAMP_UP,
        NO_RAMP_DOWN,
    }

    private DriveStraightWithRampingCommand driveCommand = null;

    public DrivetrainDriveCurveCommand(double targetDistance, RampMode ramping) {
        switch (ramping) {
            case RAMP_FULL:
                driveCommand = new DriveStraightWithRampingCommand(targetDistance);
                break;
            case NO_RAMP_UP:
                driveCommand = new DriveStraightRampDownOnlyCommand(targetDistance);
                break;
            case NO_RAMP_DOWN:
                driveCommand = new DriveStraightRampUpOnlyCommand(targetDistance);
                break;
        }

        addSequential(driveCommand);
    }

    // By default, do regular ramping
    public DrivetrainDriveCurveCommand(double targetDistance) {
        this(targetDistance, RampMode.RAMP_FULL);
    }

    // Changes the target angle at a certain distance
    public void addTurn(double distance, double targetAngle) {
        addSequential(new DrivetrainRampingSetTargetAngleAtDistanceCommand(driveCommand, distance, targetAngle));
    }

    // Changes the speed scale factor at a certain distance (slows or speeds us up basically)
    public void addSpeedChange(double distance, double speedScale) {
        addSequential(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(driveCommand, distance, speedScale));
    }


    private static class DrivetrainRampingSetSpeedScaleAtDistanceCommand extends ConditionalDistanceEncodersCommand {

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
                
                System.out.println("[DrivetrainRampSetSpeed] set to " + speedScaleFactor + "!");
            }
        }
    }

    private static class DrivetrainRampingSetTargetAngleAtDistanceCommand extends ConditionalDistanceEncodersCommand {

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
            System.out.println("[SetTargetAngle] Set!");
        }

        private static class DrivetrainRampingSetTargetAngleCommand extends InstantCommand {
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
    }

}
