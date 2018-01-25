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
public interface RobotMap {

    /**************************************************************************
     * Gamepad Ports
     *************************************************************************/
    int DRIVER_GAMEPAD_PORT = 1;
    int OPERATOR_GAMEPAD_PORT = 1;
    /**************************************************************************
     * Drivetrain motor ports
     */
    int LEFT_FRONT_MOTOR_PORT = 1;
    int LEFT_MIDDLE_MOTOR_PORT = 2;
    int LEFT_REAR_MOTOR_PORT = 3;
    
    int RIGHT_FRONT_MOTOR_PORT = 4;
    int RIGHT_MIDDLE_MOTOR_PORT = 5;
    int RIGHT_REAR_MOTOR_PORT = 6;
    /***************************************************************************
     * Encoder Ports
     */
    
    int LEFT_ENCODER_CHANNEL_A = 2;
    int LEFT_ENCODER_CHANNEL_B = 3;
    
    int RIGHT_ENCODER_CHANNEL_A = 2;
    int RIGHT_ENCODER_CHANNEL_B = 3;
}
