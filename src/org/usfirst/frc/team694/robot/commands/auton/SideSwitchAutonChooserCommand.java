package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SideSwitchAutonChooserCommand extends ConditionalCommand {

    public SideSwitchAutonChooserCommand() {
        super(new RightSideSwitchAutonCommand(), new LeftSideSwitchAutonCommand()); 
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        System.out.println("[SideSwitchChooser] uhhhhh");
    }

    @Override
    protected boolean condition() {
        System.out.println("[SideSwitchChooser] condition: " + Robot.isRobotAndSwitchOnSameSide());
        return Robot.isRobotAndSwitchOnSameSide();
    }
}