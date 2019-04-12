package org.usfirst.frc.team694.util;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

public class IRSensor {
    //Equation numbers
    //Resulted from forming an equation with graph using many coordinates and calculator
    //Power Regression used
    /******************************************************************************
     * IRSensor Constants
     *****************************************************************************/
    /******************************************************************************
     * Note that these constants were copied from the RobotMap.java from Rafael.
     * These require further testing.
     *****************************************************************************/

    //TODO: Test the IR Sensor Threshold value. Should correspond with how far away the cube is from the IR sensor.
    //Use SmartDashboard, track the IR Sensor Voltage value.
    //The current value is accurate for when the cube is about 4 inches away from the IR sensor.

//    public static final double IR_SENSOR_THRESHOLD = 1;
//    public static final double IR_TIME_IN_MECHANISM_THRESHOLD = 1.0;
//
//    public static final double EQUATION_FACTOR = 12.23368994;
//    public static final double EQUATION_EXPONENT = -0.9779601588;
//    public static final double CONVERSION_FACTOR_CM_TO_INCHES = 0.393701;
    
    private static DigitalInput cubeSensor;
    private static Relay relay;

    // Create instance of a timer that we can use to keep track of how long the
    // gear is kept in the position for.
//    private Timer timeSinceEntry;
    // We manually record whether the timer is running (Timer keeps that information private...)
//    private boolean isTimerRunning;

    public IRSensor(int port) {
        cubeSensor = new DigitalInput(port);
        relay = new Relay(0);
//        timeSinceEntry = new Timer();
//        isTimerRunning = false;
        
    }

    public boolean isSensorTriggered() {
        return !cubeSensor.get();
    }

    public void setNeutral() {
        relay.set(Relay.Value.kOff);
    }
    
    public void setForward() {
        relay.set(Relay.Value.kForward);
    }
    // The use of LEDs for the robot is currently unclear so this needs further detail in the future
    /*
    public void cubeLEDSignalControl() {
        if (isCubeDetected()) {
            Robot.ledCubeSensingSignal.stayOn();
        } else {
            Robot.ledCubeSensingSignal.stayOff();
        }
    }
    */
}