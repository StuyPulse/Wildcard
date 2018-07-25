package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.commands.auton.routines.KillerTreesDoubleCubeSwitchAutonCommand;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class KillerTreesDoubleCubeSwitchAutonChooserCommand extends ConditionalCommand {

    public KillerTreesDoubleCubeSwitchAutonChooserCommand() {
        super(new KillerTreesDoubleCubeSwitchAutonCommand(true), new KillerTreesDoubleCubeSwitchAutonCommand(false));
    }
    
    @Override
    public boolean condition() {
        return Robot.isRobotStartingOnRight();//isRobotOnRight();
    }
}
