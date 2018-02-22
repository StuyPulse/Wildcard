package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

public class RightSideSwitchAutonCommand extends CommandGroup {
    private FieldMapInterface quad = Robot.getRobotQuadrant();

    // 114: 2x speed
    private static final double DISTANCE_TOTAL = 124;

    public RightSideSwitchAutonCommand() {
        DriveStraightWithRampingCommand rampCommand = new DriveStraightNoRampingLimitCommand(DISTANCE_TOTAL);
        addSequential(new PrintCommand("[RightSideSwitch] START"));

        addParallel(new DrivetrainRampingSetSpeedScaleAtDistanceCommand(rampCommand, 0, 3)); // Move at 3x speed
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 0, 80)); // Start turning
        addParallel(new DrivetrainRampingSetTargetAngleAtDistanceCommand(rampCommand, 75, -5)); // Turn back, ish
        addParallel(new ConditionalDistanceEncodersCommand(new LiftMoveToHeightCommand(25), 40));
        // ultra fast and ultra fun but totally illegal
//        addParallel(new ConditionalDistanceEncodersCommand(new SpatulaDeacquireCommand(), 80));
        addSequential(rampCommand);

        // Fun but illegal deacquire
//        addSequential(new SpatulaDeacquireCommand(), 1);
        // Boring but legal deacquire
//        addSequential(new GrabberOpenCommand());

        //addParallel(new DrivetrainLineSensorCommand(quad.getDistanceFromLineSensorToAutoLine()));
//      addSequential(new DriveStraightWithRampingCommand(quad.getTotalDistanceToDriveForwardToReachSwitch()));
//      addSequential(new DrivetrainRotateDegreesPIDCommand(quad.getAngleToTurnToReachScaleEdge()));       
//      addSequential(new DriveStraightWithRampingCommand(quad.getDistanceToDriveForwardIntoSwitchEdge()));

//      DriveStraightWithRampingCommand rampCommand = new DriveStraightWithRampingCommand(DISTANCE_TOTAL);
      // Uncomment for no ramp down:
//      DriveStraightWithRampingCommand rampCommand = new DriveStraightRampUpOnlyCommand(DISTANCE_TOTAL);

    }

    @Override
    public void start() {
        super.start();
        System.out.println("[RightSideSwitch] start!");
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        System.out.println("[RightSideSwitch] init");
    }
    
    @Override
    protected void end() {
        super.end();
        System.out.println("[RightSideSwitch] end");
    }
}