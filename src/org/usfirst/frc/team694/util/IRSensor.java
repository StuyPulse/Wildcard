package org.usfirst.frc.team694.util;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Class for IR Sensor which can detect where cubes are
 */
public class IRSensor {
    //Equation numbers
    //Resulted from forming an equation with graph using many coordinates and calculator
    //Power Regression used

    private static AnalogInput cubeSensor;

    // Create instance of a timer that we can use to keep track of how long the
    // gear is kept in the position for.
    private Timer timeSinceEntry;
    // We manually record whether the timer is running (Timer keeps that information private...)
    private boolean isTimerRunning;

    public IRSensor() {
        cubeSensor = new AnalogInput(RobotMap.IR_SENSOR_PORT);
        timeSinceEntry = new Timer();
        isTimerRunning = false;
    }

    public static double getSensorVoltage() {
        return cubeSensor.getVoltage();
    }

    public static boolean isCubeDetected() {
        return getSensorVoltage() > SmartDashboard.getNumber("IRVoltageThreshold", RobotMap.IR_SENSOR_THRESHOLD);
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