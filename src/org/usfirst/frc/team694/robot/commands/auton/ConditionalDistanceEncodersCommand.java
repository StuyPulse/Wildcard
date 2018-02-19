package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ConditionalDistanceEncodersCommand extends Command {

    private Command onTrue;
    private double distance;

    public ConditionalDistanceEncodersCommand(Command onTrue, double distance) {
        this.onTrue = onTrue;
        this.distance = distance;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean isFinished() {
        return Robot.drivetrain.getEncoderDistance() > distance;
    }

    @Override
    protected void end() {
        onTrue.start();
    }

}
