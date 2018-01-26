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
     * Drivetrain motor ports
     *************************************************************************/
    public static final int LEFT_FRONT_MOTOR_PORT = 1;
    public static final int LEFT_MIDDLE_MOTOR_PORT = 2;
    public static final int LEFT_REAR_MOTOR_PORT = 3;

    public static final int RIGHT_FRONT_MOTOR_PORT = 4;
    public static final int RIGHT_MIDDLE_MOTOR_PORT = 5;
    public static final int RIGHT_REAR_MOTOR_PORT = 6;

    /**************************************************************************
     * Acquirer motor ports
     *************************************************************************/
    public static final int ACQUIRER_FRONT_LEFT_MOTOR_PORT = -1;
    public static final int ACQUIRER_FRONT_RIGHT_MOTOR_PORT = -1;
    public static final int ACQUIRER_BACK_LEFT_MOTOR_PORT = -1;
    public static final int ACQUIRER_BACK_RIGHT_MOTOR_PORT = -1;

    /***************************************************************************
     * Encoder Ports
     *************************************************************************/
    public static final int LEFT_ENCODER_CHANNEL_A = 0;
    public static final int LEFT_ENCODER_CHANNEL_B = 1;

    public static final int RIGHT_ENCODER_CHANNEL_A = 2;
    public static final int RIGHT_ENCODER_CHANNEL_B = 3;

    /****************************************************************************
     * Solenoid Ports
     *************************************************************************/
    public static final int GEAR_SHIFT_CHANNEL = -1;
    public static final int CRAB_ARM_SOLENOID_PORT = -1;
    public static final int CRAB_ARM_LEFT_MOTOR_PORT = -1;
    public static final int CRAB_ARM_RIGHT_MOTOR_PORT = -1;
    public static final int GRABBER_SOLENOID_PORT = -1;
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

    public static final double DRIVETRAIN_WHEEL_DIAMETER = 8;
    public static final int DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION = 256;
    public static final double DRIVETRAIN_ENCODERS_FACTOR = 4.0;
    public static final double DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION = Math.PI * DRIVETRAIN_WHEEL_DIAMETER;

    public static final double DRIVETRAIN_WHEEL_CIRCUMFERENCE = DRIVETRAIN_WHEEL_DIAMETER * Math.PI;
    public static final double DRIVETRAIN_ENCODER_INCHES_PER_PULSE = DRIVETRAIN_WHEEL_CIRCUMFERENCE / DIO_ENCODER_PULSES_PER_REVOLUTION;
            
}
