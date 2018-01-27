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
    public static final int LIFT_BRAKE_SOLENOID_CHANNEL = -2;

}
