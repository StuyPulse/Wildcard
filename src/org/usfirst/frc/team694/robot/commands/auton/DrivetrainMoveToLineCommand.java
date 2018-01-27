package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainMoveToLineCommand extends Command {
    private int linesCrossed = 0;
    private int linesToBeCrossed;
    private int leftIsAligned = 1;
    private int rightIsAligned = 1;
    private boolean isReached = false; 
    private double distance = 0;
    private int speedMode = 0;
    public DrivetrainMoveToLineCommand(int linesCrossed,double distance,double speed) {
        speedMode = (int) ((speed / 0.25) - 1);
        linesToBeCrossed = linesCrossed;
        this.distance = distance;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.resetEncoders();
        Robot.drivetrain.resetLineSensors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.tankDrive((speedMode + 1) * 0.25 * leftIsAligned, (speedMode + 1) * 0.25 * rightIsAligned);
        if (Robot.drivetrain.isOnLine(speedMode) && (!isReached)){
            linesCrossed++;
            isReached = (linesToBeCrossed == linesCrossed);
            if (isReached) {
                leftIsAligned = (Robot.drivetrain.leftIsOnLine(speedMode))? 1 : 0;
                rightIsAligned = (Robot.drivetrain.rightIsOnLine(speedMode))? 1 : 0;
                speedMode = 0;//slow down when aligning to tape   
            }
        }else{
            leftIsAligned = (Robot.drivetrain.leftIsOnLine(speedMode))? 1 : 0;
            rightIsAligned = (Robot.drivetrain.rightIsOnLine(speedMode))? 1 : 0;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (isReached && ((leftIsAligned + rightIsAligned) == 2) ) || (Math.max(Robot.drivetrain.getRightEncoderDistance(),Robot.drivetrain.getLeftEncoderDistance()) > (distance + 5));
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
