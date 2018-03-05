package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 */
public class LeftSideSwitchAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    // 114: 2x speed
    private static final double DISTANCE_TOTAL = 153;
    private final static double INITIAL_DRIVE_RAMP_TIMEOUT = 3.5;

    public LeftSideSwitchAutonCommand() {
        //addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromLineSensorToAutoLine()));
//        addSequential(new DriveStraightWithRampingCommand(226)); //TODO: Please insert another magic number
//        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));
//        addSequential(new DriveStraightWithRampingCommand(202.31)); //TODO: Please insert another magic number
//        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));
//        addSequential(new DriveStraightWithRampingCommand(37.5)); //TODO: Please insert another magic number
//        addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));
//        addSequential(new DriveStraightWithRampingCommand(20)); //TODO: Please insert magic number
//        addSequential(new GrabberOpenCommand());
//        addSequential(new SpatulaDeacquireCommand());
//        DriveStraightWithRampingCommand rampCommand = new DriveStraightRampUpOnlyCommand(DISTANCE_TOTAL);

        setInterruptible(false);

        addSequential(new PrintCommand("[LeftSideSwitchAuton] start!"));

        addSequential(new LeftSideSwitchAutonPartOneCommand(), INITIAL_DRIVE_RAMP_TIMEOUT);

        addSequential(new PrintCommand("[LeftSideSwitchAuton] Post score"));

        addSequential(new SwitchPostScoreExchangeScoreCommand(false));

    }

    @Override
    protected void end() {
        super.end();
        System.out.println("[LeftSideSwitchAuton] END");
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        System.out.println("[LeftSideSwitchAuton] INTERRUPTED");
    }

    @Override
    public void cancel() {
        // Don't cancel!
    }

    private static class LeftSideSwitchAutonPartOneCommand extends CommandGroup {
        public LeftSideSwitchAutonPartOneCommand() {
            DriveStraightWithRampingCommand rampCommand = new DriveStraightNoRampingLimitCommand(DISTANCE_TOTAL);

//            addSequential(new DrivetrainLowGearCommand()); 
            addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand, 0, 1), INITIAL_DRIVE_RAMP_TIMEOUT); // Move at 0.3 speed
            addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 0, -80), INITIAL_DRIVE_RAMP_TIMEOUT); // Start turning
            addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 65, 5), INITIAL_DRIVE_RAMP_TIMEOUT); // Turn back, ish
            addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(30), 40), INITIAL_DRIVE_RAMP_TIMEOUT);
            // ultra fast and ultra fun but totally illegal
//            addParallel(new ConditionalDistanceEncodersCommand(new SpatulaDeacquireCommand(), 95));

//            addParallel(new ConditionalDistanceEncodersCommand(new SpatulaDeacquireCommand(), 95), INITIAL_DRIVE_RAMP_TIMEOUT);
            addParallel(new ConditionalDistanceEncodersCommand(
                    new SideSwitchAutonChooserCommand.SpatulaDeacquireTimeCommand(), 95), INITIAL_DRIVE_RAMP_TIMEOUT);
            addSequential(new PrintCommand("[LeftSideSwitchAuton] Sequentials finished initializing..."));
            addSequential(rampCommand, INITIAL_DRIVE_RAMP_TIMEOUT);
        }

        @Override
        public void initialize() {
            super.initialize();
            setTimeout(INITIAL_DRIVE_RAMP_TIMEOUT);
        }

        @Override
        public boolean isFinished() {
            return super.isFinished() || isTimedOut();
        }
        
        @Override
        public void execute() {
            super.execute();
            System.out.println("done? " + isFinished());
        }

        @Override
        public void end() {
            super.end();
            System.out.println("[LeftSideSwitch Part 1] DONE!");
        }

    }
}
