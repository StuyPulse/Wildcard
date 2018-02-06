/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

public final class RobotMap {

    /**************************************************************************
     * Gamepad Ports
     *************************************************************************/
    public static final int DRIVER_GAMEPAD_PORT = 0;
    public static final int OPERATOR_GAMEPAD_PORT = 1;

    /**************************************************************************
     * Drivetrain Motor Ports
     *************************************************************************/
    public static final int LEFT_TOP_MOTOR_PORT = -1;
    public static final int LEFT_MIDDLE_MOTOR_PORT = -1;
    public static final int LEFT_BOTTOM_MOTOR_PORT = -1;

    public static final int RIGHT_TOP_MOTOR_PORT = -1;
    public static final int RIGHT_MIDDLE_MOTOR_PORT = -1;
    public static final int RIGHT_BOTTOM_MOTOR_PORT = -1;

    /**************************************************************************
     * Spatula Motor Ports
     *************************************************************************/
    public static final int SPATULA_LEFT_MOTOR_PORT = -1;
    public static final int SPATULA_RIGHT_MOTOR_PORT = -1;

    /**************************************************************************
     * Spatula Limit Switch
     *************************************************************************/
    public static final int SPATULA_LIMIT_SWITCH_PORT = -1;
    
    /***************************************************************************
     * Lift Motor Ports
     *************************************************************************/
    public static final int INNER_LEFT_LIFT_MOTOR_PORT = -1;
    public static final int INNER_RIGHT_LIFT_MOTOR_PORT = -1;
    public static final int OUTER_LEFT_LIFT_MOTOR_PORT = -1;
    public static final int OUTER_RIGHT_LIFT_MOTOR_PORT = -1;

    /***************************************************************************
     * Lift Constants
     *************************************************************************/
    public static final double DIAMETER_OF_ENCODER_SPROCKET = 2.873; 
    public static final double TOTAL_CARRIAGE_MOVEMENT = 95.25;
    public static final double LIFT_ENCODER_RAW_MULTIPLIER = DIAMETER_OF_ENCODER_SPROCKET * Math.PI * 3 / 1024;
    public static final double MIN_HEIGHT_OF_LIFT = 23;
    public static final double MAX_HEIGHT_OF_LIFT = TOTAL_CARRIAGE_MOVEMENT + MIN_HEIGHT_OF_LIFT;

    /***************************************************************************
     * Lift Limit Switch
     *************************************************************************/
    public static final int LIFT_TOP_LIMIT_SWITCH_PORT = -1;
    public static final int LIFT_BOTTOM_LIMIT_SWITCH_PORT = -1;
    
    /****************************************************************************
     * Solenoid Ports
     *************************************************************************/
    public static final int GEAR_SHIFT_CHANNEL = -1;
    public static final int CRAB_ARM_DEPLOY_SOLENOID_PORT = -1;
    public static final int CRAB_ARM_FLAPPER_SOLENOID_PORT = -1;
    public static final int CRAB_ARM_LEFT_MOTOR_PORT = -1;
    public static final int CRAB_ARM_RIGHT_MOTOR_PORT = -1;
    public static final int GRABBER_SOLENOID_OPEN = -1;
    public static final int GRABBER_SOLENOID_CLOSE = -1;

    public static final int LIFT_BRAKE_SOLENOID_CHANNEL = -1;
    public static final int SPATULA_FLIP_SOLENOID_PORT = -1;
    public static final int SPATULA_TONGS_SOLENOID_PORT = -1; 

    public static final int LIFT_BRAKE_SOLENOID_PORT = -1;
    public static final int ACQUIRER_FLIP_SOLENOID_PORT = -1;
    public static final int ACQUIRER_SQUEEZE_SOLENOID_PORT = -1; 

    /*****************************************************************************
     * Analog Ports
     ****************************************************************************/
    public static final int DRVETRAIN_LINE_SENSOR_LEFT_PORT = -1;
    public static final int DRVETRAIN_LINE_SENSOR_RIGHT_PORT = -1;

    /******************************************************************************
     * Drivetrain Encoder/Movement Constants
     *****************************************************************************/

    public static final double DRIVETRAIN_WHEEL_DIAMETER = 6.0;
    public static final int DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION = 256;
    public static final double DRIVETRAIN_ENCODERS_FACTOR = 4.0;
    public static final double DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION = Math.PI * DRIVETRAIN_WHEEL_DIAMETER;
    public static final double DRIVETRAIN_WHEEL_CIRCUMFERENCE = DRIVETRAIN_WHEEL_DIAMETER * Math.PI;
    
    /** DRIVETRAIN_RAW_MULTIPLIER: We multiply by 4 because the encoder has 4 Quadrants, and each Quadrant passes 256 pulses. **/
    public static final double DRIVETRAIN_RAW_MULTIPLIER = DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION / DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION;
    

}
