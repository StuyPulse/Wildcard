package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class QuisitorFlipDownIfUpCommand extends ConditionalCommand {

    public QuisitorFlipDownIfUpCommand() {
        super(new FlapAndFlipDownCommand());
        //setInterruptible(false);
    }

    @Override
    protected boolean condition() {
        return Robot.quisitor.isQuisitorUp();
    }
}
