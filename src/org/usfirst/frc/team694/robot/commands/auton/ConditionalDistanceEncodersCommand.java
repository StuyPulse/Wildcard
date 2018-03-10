package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ConditionalDistanceEncodersCommand extends Command {

    private Command onTrue;
    private double distance;

    private double startDistance;

    public ConditionalDistanceEncodersCommand(Command onTrue, double distance) {
        this.onTrue = onTrue;
        this.distance = distance;
    }

    @Override
    protected void initialize() {
        startDistance = Robot.drivetrain.getEncoderDistance();
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getEncoderDistance() - startDistance) > distance;
    }

    @Override
    protected void end() {
        onTrue.start();
    }

}
