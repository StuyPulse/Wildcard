package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * A command that lets us trick DriveStraightPID to turn and change speed mid
 * trajectory (wildcard motion profiling)
 */

public class DrivetrainDriveCurveCommand extends CommandGroup {

    public enum RampMode {
        RAMP_FULL, NO_RAMP_UP, NO_RAMP_DOWN, NO_RAMPING
    }

    private DriveStraightPIDCommand driveCommand = null;

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
        case NO_RAMPING:
            driveCommand = new DriveStraightPIDCommand(targetDistance, 1);
            break;
        }
        addParallel(driveCommand); // Doing this so that we can make everything run at once
    }

    // By default, do regular ramping
    public DrivetrainDriveCurveCommand(double targetDistance) {
        this(targetDistance, RampMode.RAMP_FULL);
    }

    @Override
    protected void interrupted() {
        System.out.println("[DrivetrainDriveCurve] INTERRUPT");
    }

    // Changes the target angle at a certain distance
    public void addTurn(double distance, double targetAngle) {
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(driveCommand, distance, targetAngle));
    }

    // Changes the speed scale factor at a certain distance (slows or speeds us up basically)
    public void addSpeedChange(double distance, double speedScale) {
        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(driveCommand, distance, speedScale));
    }

    private static class DrivetrainRampingSetSpeedScaleAtDistanceCommand extends ConditionalDistanceEncodersCommand {

        public DrivetrainRampingSetSpeedScaleAtDistanceCommand(DriveStraightPIDCommand rampCommand, double distance,
                double factor) {
            super(new DrivetrainRampingSetSpeedScaleCommand(rampCommand, factor), distance);
        }

        private static class DrivetrainRampingSetSpeedScaleCommand extends InstantCommand {
            private DriveStraightPIDCommand rampCommand;
            private double speedScaleFactor;

            public DrivetrainRampingSetSpeedScaleCommand(DriveStraightPIDCommand rampCommand, double speedScaleFactor) {
                this.rampCommand = rampCommand;
                this.speedScaleFactor = speedScaleFactor;
            }

            @Override
            protected void initialize() {
                rampCommand.setSpeedScale(speedScaleFactor);
                System.out.println("[DrivetrainRampSetSpeed] set to " + speedScaleFactor + " at "
                        + +Robot.drivetrain.getEncoderDistance() + "!");
            }
        }
    }

    private static class DrivetrainRampingSetTargetAngleAtDistanceCommand extends ConditionalDistanceEncodersCommand {

        public DrivetrainRampingSetTargetAngleAtDistanceCommand(DriveStraightPIDCommand rampCommand, double distance,
                double angle) {
            super(new DrivetrainRampingSetTargetAngleCommand(rampCommand, angle), distance);
        }

        @Override
        protected void initialize() {
            super.initialize();
            System.out.println("[SetTargetAngle] Set at " + Robot.drivetrain.getEncoderDistance());
        }

        private static class DrivetrainRampingSetTargetAngleCommand extends InstantCommand {
            private double targetAngle;
            private DriveStraightPIDCommand rampCommand;

            public DrivetrainRampingSetTargetAngleCommand(DriveStraightPIDCommand rampCommand, double targetAngle) {
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
