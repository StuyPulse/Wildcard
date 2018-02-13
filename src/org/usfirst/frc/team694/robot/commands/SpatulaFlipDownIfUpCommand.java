package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
/**
 *This will flip the cube when the IR sensor detects that the cube is not upright, and leave it when it detects the cube is upright
 */
public class SpatulaFlipDownIfUpCommand extends ConditionalCommand {

    public SpatulaFlipDownIfUpCommand() {
        super(new FlapAndFlipDownCommand());
    }

    @Override
    protected boolean condition() {
        return Robot.spatula.isSpatulaUp();
    }
}
