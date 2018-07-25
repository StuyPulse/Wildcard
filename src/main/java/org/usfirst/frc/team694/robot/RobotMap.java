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

    public static final int DRIVETRAIN_RIGHT_TOP_MOTOR_PORT = 6;
    public static final int DRIVETRAIN_RIGHT_MIDDLE_MOTOR_PORT = 5;
    public static final int DRIVETRAIN_RIGHT_BOTTOM_MOTOR_PORT = 4;

    /**************************************************************************
     * QUISITOR Motor Ports
     *************************************************************************/
    public static final int QUISITOR_MOTOR_PORT = 11;

    /**************************************************************************
     * QUISITOR Sensors
     *************************************************************************/
    public static final int QUISITOR_LIMIT_SWITCH_PORT = 1;
    public static final int QUISITOR_IR_SENSOR_PORT = 8;

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
    // Extra factor empirically determined
    private static final double LIFT_EMPERICAL_RAW_MULTIPLIER = (45 + 46.5 + 39)/(41.125 + 42.199 + 35.44);

    public static final double LIFT_DIAMETER_OF_ENCODER_SPROCKET = 2.873; //unchanged for new
    public static final double LIFT_TOTAL_CARRIAGE_MOVEMENT = 92.25; //unchanged for new
    public static final double ENCODER_TO_SPROCKET_REDUCTION = 1 / 106.94; //(1.0 / 4) * (1.0 / 3); // updated
    public static final double LIFT_ENCODER_RAW_MULTIPLIER = 
            LIFT_EMPERICAL_RAW_MULTIPLIER * (LIFT_DIAMETER_OF_ENCODER_SPROCKET * Math.PI * 3 / 1024) 
            / 4.4 * ENCODER_TO_SPROCKET_REDUCTION; 

    public static final double MIN_HEIGHT_OF_LIFT = 20.5; // unchanged
    public static final double MAX_HEIGHT_OF_LIFT = LIFT_TOTAL_CARRIAGE_MOVEMENT + MIN_HEIGHT_OF_LIFT; //unchanged
    public static final double LIFT_MAX_SPEED = 1;
    public static final double LIFT_BACKDRIVE_SPEED = 0.16667; //piotr says wont change much

    public static final double LIFT_MIN_SPEED = 0.1;//0.25;
    public static final double LIFT_RAMP_HEIGHT_THRESHOLD = 30;//20.0; //adris says wont change
    public static final double LIFT_RAMP_SLOPE = (LIFT_MAX_SPEED - LIFT_MIN_SPEED) / LIFT_RAMP_HEIGHT_THRESHOLD;
    public static final double LIFT_JOYSTICK_MOVE_THRESHOLD = .05;
    public static final double LIFT_CLOSE_ENOUGH_HEIGHT_THRESHOLD = 2;


    /***************************************************************************
     * Lift Limit Switch
     *************************************************************************/
    public static final int LIFT_TOP_LIMIT_SWITCH_PORT = 2;
    public static final int LIFT_BOTTOM_LIMIT_SWITCH_PORT = 0;

    /****************************************************************************
     * Solenoid Ports
     *************************************************************************/
    public static final int GEAR_SHIFT_CHANNEL = 7;

    public static final int QUISITOR_GRABBER_SOLENOID_OPEN_PORT = 3;//Left 
    public static final int QUISITOR_GRABBER_SOLENOID_CLOSE_PORT = 2;//Right
    public static final int QUISITOR_GRABBER_SOLENOID_OPEN_PORT_MILDCARD = 6;//Left 
    public static final int QUISITOR_GRABBER_SOLENOID_CLOSE_PORT_MILDCARD = 5;//Right

    /*****************************************************************************
     * Analog Ports
     ****************************************************************************/

    // Temporary module
    public static final int DIO_PI_PORT = 1; // Temporary digital output for if the Arduino LEDs don't work
    public static final int DRIVETRAIN_LINE_SENSOR_LEFT_PORT = 1;
    public static final int DRIVETRAIN_LINE_SENSOR_RIGHT_PORT = 2;
    public static final int FRONT_SONAR_INPUT_PORT = 6;
    public static final int FRONT_SONAR_OUTPUT_PORT = 5;
    public static final int REAR_SONAR_INPUT_PORT = 4;
    public static final int REAR_SONAR_OUTPUT_PORT = 3;
    public static final int IR_SENSOR_CUBE = 8;
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

    // Extra factor imperically determined
    private static final double DRIVETRAIN_EMPERICAL_RAW_MULTIPLIER = (63.7 / 63.0) * 61.1 / ((463.544 + 461.814) / 2.0);//163/1246.0;

    public static final double DRIVETRAIN_RAW_MULTIPLIER = 
            DRIVETRAIN_EMPERICAL_RAW_MULTIPLIER * DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION
            / (DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION * DRIVETRAIN_ENCODERS_FACTOR);

    /******************************************************************************
     * LED Device Addresses
     ******************************************************************************/
    public static final int LIFTLIGHTING_ADDRESS = 95;
}

