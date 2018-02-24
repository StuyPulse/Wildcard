package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class SpatulaFlipDownIfUpCommand extends ConditionalCommand {

    public SpatulaFlipDownIfUpCommand() {
        super(new FlapAndFlipDownCommand());
        //setInterruptible(false);
    }

    @Override
    protected boolean condition() {
        return Robot.spatula.isSpatulaUp();
    }
}
