package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class AlternativeCurvySwitchAutonChooserCommand extends ConditionalCommand {

    public AlternativeCurvySwitchAutonChooserCommand() {
        super(new AlternativeCurvySwitchAutonCommand(true), new AlternativeCurvySwitchAutonCommand(false)); 
        System.out.println("[SideSwitch] IS RIGHT? " + Robot.isRobotStartingOnRight());
    }

    @Override
    protected boolean condition() {
        return Robot.isSwitchOnRight();
    }

}