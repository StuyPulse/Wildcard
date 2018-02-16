package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.FieldMapInterface;
import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.CrabArmDeployCommand;
import org.usfirst.frc.team694.robot.commands.DrivetrainRotateDegreesPIDCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.SpatulaFlipDownCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SameSideBottomAngledSwitchCommand extends CommandGroup {
    private FieldMapInterface Quad = Robot.getRobotQuadrant();
    private double speed = 0.5;
    public SameSideBottomAngledSwitchCommand() {
        addSequential(new DrivetrainMoveToLineCommand(Quad.getDistanceFromLineSensorToAutoLine(), speed));
        addSequential(new CrabArmDeployCommand());
        //TODO: Create constant/method and fix problems with constants
        //addSequential(new DrivetrainMoveInchesEncoderCommand(speed, Quad.getDistanceFromAutoLineToMiddelOfSwitch));
        //TODO: Not sure if the angle degree should be a constant in field map or not
        addSequential(new DrivetrainRotateDegreesPIDCommand(-90));
        addSequential(new SpatulaFlipDownCommand());
        addSequential(new SpatulaDeacquireCommand());
    }
}
