/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot;

public final class RobotMap {
    /**************************************************************************
     * Robot Dimensions
     */
    public static final double DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT = 3.75; //
    public static final double LENGTH_OF_BOT = 38; // (not including QUISITOR)
    public static final double WIDTH_OF_BOT = 34.5; //
    public static final double MIDDLE_OF_BOT_LENGTHWISE = LENGTH_OF_BOT / 2;
    public static final double MIDDLE_OF_BOT_WIDTHWISE = WIDTH_OF_BOT / 2;
    public static final double DIFFERENCE_BETWEEN_LENGTH_OF_ROBOT_AND_WIDTH_OF_ROBOT_AFTER_TURN = (LENGTH_OF_BOT - WIDTH_OF_BOT) / 2;
    /**************************************************************************
     * Gamepad Ports
     *************************************************************************/
    public static final int DRIVER_GAMEPAD_PORT = 0;
    public static final int OPERATOR_GAMEPAD_PORT = 1;

    /**************************************************************************
     * Drivetrain Motor Ports
     *************************************************************************/
    public static final int DRIVETRAIN_LEFT_TOP_MOTOR_PORT = 3;
    public static final int DRIVETRAIN_LEFT_MIDDLE_MOTOR_PORT = 2;
    public static final int DRIVETRAIN_LEFT_BOTTOM_MOTOR_PORT = 1;

    /**************************************************************************
     * QUISITOR and CrabArm Constants //All times are in seconds
     *************************************************************************/
    public static final double FLAP_OUT_SPEED = 1;

    public static final int DRIVETRAIN_RIGHT_TOP_MOTOR_PORT = 6;
    public static final int DRIVETRAIN_RIGHT_MIDDLE_MOTOR_PORT = 5;
    public static final int DRIVETRAIN_RIGHT_BOTTOM_MOTOR_PORT = 4;

    /**************************************************************************
     * QUISITOR Motor Ports
     *************************************************************************/
    public static final int QUISITOR_MOTOR_PORT = 12; //TODO: Find out actual port from electronics

    /**************************************************************************
     * QUISITOR Limit Switch
     *************************************************************************/
    public static final int QUISITOR_LIMIT_SWITCH_PORT = 1;

    /***************************************************************************
     * Lift Motor Ports
     *************************************************************************/
    public static final int LIFT_INNER_LEFT_MOTOR_PORT = 7;
    public static final int LIFT_INNER_RIGHT_MOTOR_PORT = 9;
    public static final int LIFT_OUTER_LEFT_MOTOR_PORT = 8;
    public static final int LIFT_OUTER_RIGHT_MOTOR_PORT = 10;

    /***************************************************************************
     * Lift Constants
     *************************************************************************/
    public static final double LIFT_DIAMETER_OF_ENCODER_SPROCKET = 2.873;
    public static final double LIFT_TOTAL_CARRIAGE_MOVEMENT = 92.25;
    public static final double LIFT_ENCODER_RAW_MULTIPLIER = (LIFT_DIAMETER_OF_ENCODER_SPROCKET * Math.PI * 3 / 1024) / 4.4;
    
    public static final double MIN_HEIGHT_OF_LIFT = 20.5;
    public static final double MAX_HEIGHT_OF_LIFT = LIFT_TOTAL_CARRIAGE_MOVEMENT + MIN_HEIGHT_OF_LIFT;
    public static final double LIFT_MAX_SPEED = 1;
    public static final double LIFT_BACKDRIVE_SPEED = 0.16667;

    public static final double LIFT_MIN_SPEED = 0.25;
    public static final double LIFT_RAMP_HEIGHT_THRESHOLD = 20.0;
    public static final double LIFT_RAMP_SLOPE = (LIFT_MAX_SPEED - LIFT_MIN_SPEED) / LIFT_RAMP_HEIGHT_THRESHOLD;
    public static final double LIFT_JOYSTICK_MOVE_THRESHOLD = .05;
    public static final double LIFT_CLOSE_ENOUGH_HEIGHT_THRESHOLD = 2;


    /***************************************************************************
     * Lift Limit Switch
     *************************************************************************/
    public static final int LIFT_TOP_LIMIT_SWITCH_PORT = 2;
    public static final int LIFT_BOTTOM_LIMIT_SWITCH_PORT = 0;

    /****************************************************************************
     * CrabArm Ports
     *************************************************************************/

    public static final int CRAB_ARM_LEFT_MOTOR_PORT = 13;
    public static final int CRAB_ARM_RIGHT_MOTOR_PORT = 14;

    /****************************************************************************
     * Solenoid Ports
     *************************************************************************/
    public static final int GEAR_SHIFT_CHANNEL = 7;

    public static final int QUISITOR_GRABBER_SOLENOID_LEFT_PORT = 2;//Left 
    public static final int QUISITOR_GRABBER_SOLENOID_RIGHT_PORT = 3;//Right

    //    public static final int QUISITOR_TONGS_SOLENOID_PORT = -1; 

    /*****************************************************************************
     * Analog Ports
     ****************************************************************************/

    public static final int DRIVETRAIN_LINE_SENSOR_LEFT_PORT = 1;
    public static final int DRIVETRAIN_LINE_SENSOR_RIGHT_PORT = 2;

    /******************************************************************************
     * Drivetrain Encoder/Movement Constants
     *****************************************************************************/

    public static final double DRIVETRAIN_WHEEL_DIAMETER = 6.0;
    public static final int DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION = 256;
    public static final int DRIVETRAIN_ENCODERS_FACTOR = 4;
    public static final double DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION = Math.PI * DRIVETRAIN_WHEEL_DIAMETER;

    /**
     * DRIVETRAIN_RAW_MULTIPLIER: We multiply by 4 because the encoder has 4
     * Quadrants, and each Quadrant passes 256 pulses.
     **/
    public static final double DRIVETRAIN_RAW_MULTIPLIER = 
            /*(127.0 / 121.0) * (161.5 / 1210.0) * */
            // 0.98 scale tested after driving a certain distance
            // Mildcard factor:
            /*0.98 * (296.0 / 2171.0)*/ 
            (72.5 / 71.5) * (83.0 / 632.1) * DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION
            / (DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION * DRIVETRAIN_ENCODERS_FACTOR);


    /******************************************************************************
     * Crab Arm Flap Constants
     ******************************************************************************/
    public static final double CRAB_ARM_COAST_POWER = 0.5; 
    // Needs to be tested out, coast should exert less power than push, has to be enough to keep crab arms open but not overextending
    public static final double CRAB_ARM_PUSH_POWER = 0.5; 
    // Needs to be tested out, should be more than coast, the power to push the crab arm back initially before it stays open
}

