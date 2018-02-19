package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class ConditionalDistanceEncodersCommand extends ConditionalCommand {

    private double distance;

    public ConditionalDistanceEncodersCommand(Command onTrue, double distance) {
        super(onTrue);
        this.distance = distance;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean condition() {
        return Robot.drivetrain.getEncoderDistance() > distance;
    }

}
