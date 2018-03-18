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
        startDistance = Robot.drivetrain.getEncoderMax();
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getEncoderMax() - startDistance) > distance;
    }

    @Override
    protected void end() {
        System.out.println("[ConditionalEncoderCommand] start: " + startDistance + "delta: " + (Robot.drivetrain.getEncoderDistance() - startDistance) + ".");
        onTrue.start();
    }

}
