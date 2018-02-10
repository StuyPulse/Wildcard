package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DrivetrainMoveToLineCommand extends Command {
    private boolean isReached = false; 
    private double distance = 0;
    private double speed = 1;
    public DrivetrainMoveToLineCommand(double distance,double speed) {
        this.distance = distance;//represents length in inches to go, just in case line sensing fails.
        requires(Robot.drivetrain);
    }

    protected void initialize() {
        Robot.drivetrain.resetEncoders();
        //Robot.drivetrain.resetLineSensors();
    }

    protected void execute() {
        //Robot.drivetrain.updateSensors();
        Robot.drivetrain.tankDrive(speed, speed);
        //isReached = Robot.drivetrain.isOnLine((int) speed);
    }

    protected boolean isFinished() {
        return isReached || (Robot.drivetrain.getEncoderDistance() > distance);
    }

    protected void end() {
        Robot.drivetrain.resetEncoders();
    }

    protected void interrupted() {
    }
}