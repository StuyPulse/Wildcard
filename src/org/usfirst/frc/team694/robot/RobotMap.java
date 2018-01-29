/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public final class RobotMap {

    /**************************************************************************
     * Gamepad Ports
     *************************************************************************/
    public static final int DRIVER_GAMEPAD_PORT = 1;
    public static final int OPERATOR_GAMEPAD_PORT = 1;
    /**************************************************************************
     * Drivetrain Motor Ports
     *************************************************************************/
    public static final int LEFT_FRONT_MOTOR_PORT = 1;
    public static final int LEFT_MIDDLE_MOTOR_PORT = 2;
    public static final int LEFT_REAR_MOTOR_PORT = 3;

    public static final int RIGHT_FRONT_MOTOR_PORT = 4;
    public static final int RIGHT_MIDDLE_MOTOR_PORT = 5;
    public static final int RIGHT_REAR_MOTOR_PORT = 6;

    /**************************************************************************
     * Acquirer Motor Ports
     *************************************************************************/
    public static final int ACQUIRER_LEFT_MOTOR_PORT = -1;
    public static final int ACQUIRER_RIGHT_MOTOR_PORT = -1;

    /***************************************************************************
     * Lift Motor Ports
     *************************************************************************/
    public static final int LEFT_LIFT_MOTOR_PORT = -1;
    public static final int RIGHT_LIFT_MOTOR_PORT = -1; 
    
    /***************************************************************************
     * Lift Constants
     *************************************************************************/
    public static final double LIFT_RAW_MULTIPLIER = -1;

    /****************************************************************************
     * Solenoid Ports
     *************************************************************************/
    public static final int GEAR_SHIFT_CHANNEL = -1;
    public static final int CRAB_ARM_SOLENOID_PORT = -1;
    public static final int CRAB_ARM_LEFT_MOTOR_PORT = -1;
    public static final int CRAB_ARM_RIGHT_MOTOR_PORT = -1;
    public static final int GRABBER_SOLENOID_PORT = -1;
    public static final int LIFT_BRAKE_SOLENOID_CHANNEL = -1;

    /*****************************************************************************
     * Analog Ports
     ****************************************************************************/
    public static final int DRVETRAIN_LINE_SENSOR_LEFT_PORT = -1;
    public static final int DRVETRAIN_LINE_SENSOR_RIGHT_PORT = -1;
    
    /******************************************************************************
     * Line Sensor Constants
     *****************************************************************************/
    public static final int DRIVETRAIN_LINE_SENSOR_INITIALIZE_TIME = 3;
    public static final int DIO_ENCODER_PULSES_PER_REVOLUTION = 360;

    public static final double DRIVETRAIN_WHEEL_DIAMETER = 6.0;
    public static final int DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION = 256;
    public static final double DRIVETRAIN_ENCODERS_INCHES_PER_PULSE = 1 / (512 / 3);
    public static final double DRIVETRAIN_ENCODERS_FACTOR = 4.0;
    public static final double DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION = Math.PI * DRIVETRAIN_WHEEL_DIAMETER;
    public static final double DRIVETRAIN_RAW_MULTIPLIER = DRIVETRAIN_ENCODERS_INCHES_PER_PULSE;
    
    public static final double DRIVETRAIN_WHEEL_CIRCUMFERENCE = DRIVETRAIN_WHEEL_DIAMETER * Math.PI;
    public static final double DRIVETRAIN_ENCODER_INCHES_PER_PULSE = DRIVETRAIN_WHEEL_CIRCUMFERENCE / DIO_ENCODER_PULSES_PER_REVOLUTION;
    
    public static final int ACQUIRER_SOLENOID_PORT = -1;
    
    /******************************************************************************
     * IRSensor Constants
     *****************************************************************************/
    /******************************************************************************
     * Note that these constants were copied from the RobotMap.java from Rafael. 
     * These require further testing. 
     *****************************************************************************/ 
    
    public static final int IR_SENSOR_PORT = -1;
    public static final int BOTTOM_IR_SENSOR_PORT = -1;
    //TODO: Test the IR Sensor Threshold value. Should correspond with how far away the cube is from the IR sensor.
    //The current value is accurate for when the cube is about 4 inches away from the IR sensor.
    public static final double IR_SENSOR_THRESHOLD = 1;
    public static final double IR_TIME_IN_MECHANISM_THRESHOLD = 1.0;

    public static final double EQUATION_FACTOR = 12.23368994;
    public static final double EQUATION_EXPONENT = -0.9779601588;
    public static final double CONVERSION_FACTOR_CM_TO_INCHES = 0.393701;
   
}
