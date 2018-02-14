package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.GrabberOpenCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DifferentSideScaleAutonCommand extends CommandGroup {
    public static FieldMapInterface Quad = Robot.currentQuad;
    public double speed = 0.5;

    public DifferentSideScaleAutonCommand() {  
        addSequential(new DrivetrainMoveToLineCommand(speed, Quad.getDistanceFromLineSensorToAutoLine()));
        addSequential(new DrivetrainMoveInchesEncoderCommand(speed, Quad.getDistanceToTravelBeforeFirstTurn()));
        addSequential(new DrivetrainRotateDegreesPIDCommand(Quad.getDegreeOfAngleToTurnToReachPlatformZone()));
        addSequential(new DrivetrainMoveToLineCommand(speed, Quad.getDistanceToTravelToReachPlatformZoneAfterTurn()));
        addSequential(new DrivetrainMoveToLineCommand(speed, Quad.getDistanceToTravelToReachOtherPlatformZoneEdge()));
        addSequential(new DrivetrainMoveInchesEncoderCommand(speed, Quad.getDistanceToDriveOutAfterPlatformZoneEdge()));
        addSequential(new DrivetrainRotateDegreesPIDCommand(Quad.getDegreeOfAngleToTurnToReachScaleSide()));

        addSequential(new DrivetrainMoveToLineCommand(speed, Quad.getDistanceToTravelToReachScaleSide()));
        addSequential(new GrabberOpenCommand());
    }
}