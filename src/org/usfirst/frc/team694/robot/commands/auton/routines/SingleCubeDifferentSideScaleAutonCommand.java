package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.LiftMoveToHeightCommand;
import org.usfirst.frc.team694.robot.commands.QuisitorDeacquireCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightPIDCommand;
import org.usfirst.frc.team694.robot.commands.auton.DriveStraightRampDownOnlyCommand;
import org.usfirst.frc.team694.robot.commands.auton.DrivetrainRotateAbsoluteDegreesPIDCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SingleCubeDifferentSideScaleAutonCommand extends CommandGroup {

    public SingleCubeDifferentSideScaleAutonCommand() {
        // Add Commands here:
        //TODO: kill magic numbers
        addParallel(new DriveStraightRampDownOnlyCommand(235));
        //TODO: Make constructor for allowing differing inital speeds for ramping
        addSequential(new LiftMoveToHeightCommand(5));      
        
        //addSequential(new DrivetrainDriveCurveCommand(69.4));//Distance? Ramping or no Ramping?
        //No arc command yet?
        
        addSequential(new DriveStraightRampDownOnlyCommand(-234));
        
        addSequential(new DrivetrainRotateAbsoluteDegreesPIDCommand(Robot.isRobotStartingOnRight() ? -45 : 45) );
        
        addParallel(new LiftMoveToHeightCommand(75));//TODO: Ask engineering
        addSequential(new DriveStraightPIDCommand(55, 0.3));
        
        addSequential(new QuisitorDeacquireCommand(), 2);//TODO: ask for timeout
    }
}
