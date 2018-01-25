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
    
    int ACQUIRER_FRONT_LEFT_MOTOR_PORT = -1;
    int ACQUIRER_FRONT_RIGHT_MOTOR_PORT = -1;
    int ACQUIRER_BACK_LEFT_MOTOR_PORT = -1;
    int ACQUIRER_BACK_RIGHT_MOTOR_PORT = -1;
    double ACQUIRER_MOTOR_SPEED = -1;
}
