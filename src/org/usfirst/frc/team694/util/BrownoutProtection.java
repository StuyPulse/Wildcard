package org.usfirst.frc.team694.util;

import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team694.robot.Robot;

public class BrownoutProtection {
        
    private double meanVoltageValue;
    private double meanCurrentValue;
    private double leftDrivetrainVoltage;
    private double updatedLeftDrivetrainVoltage;
    private double rightDrivetrainVoltage;
    private double updatedRightDrivetrainVoltage;
    private double liftVoltage;
    private double updatedLiftVoltage;
    private double quisitorVoltage;
    private double updatedQuisitorVoltage;
    private double requestedVoltage;

    public BrownoutProtection() {
        //TODO: What to put in the constructor? Not a port argument because it's not a sensor...
    }
    
    // Method used to stop.

 public void reset() {
        Robot.drivetrain.stop();
        Robot.lift.stop();
        Robot.quisitor.stop();
    }
    
    public void voltageLoop(){
        leftDrivetrainVoltage = Robot.drivetrain.getLeftSpeed();
        rightDrivetrainVoltage = Robot.drivetrain.getRightSpeed();
        liftVoltage = Robot.lift.getSpeed();
        //quisitorVoltage = Robot.quisitor.getSpeed();
    }
    
    public double findRequestedVoltage() {
        //TODO: Find requested speed values and set values for updated motor voltages.
        requestedVoltage = leftDrivetrainVoltage + rightDrivetrainVoltage + liftVoltage + quisitorVoltage;
        return requestedVoltage;
    }
    
}
