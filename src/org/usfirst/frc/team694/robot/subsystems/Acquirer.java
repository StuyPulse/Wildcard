package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Acquirer extends Subsystem {

    public static WPI_VictorSPX leftFrontAcquirerMotor;
    public static WPI_VictorSPX rightFrontAcquirerMotor;
    public static WPI_VictorSPX rightBackAcquirerMotor;
    public static WPI_VictorSPX leftBackAcquirerMotor;
        
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Acquirer() {
        leftFrontAcquirerMotor = new WPI_VictorSPX(RobotMap.ACQUIRER_FRONT_LEFT_MOTOR_PORT);
        rightFrontAcquirerMotor = new WPI_VictorSPX(RobotMap.ACQUIRER_FRONT_RIGHT_MOTOR_PORT);
        leftBackAcquirerMotor = new WPI_VictorSPX(RobotMap.ACQUIRER_BACK_LEFT_MOTOR_PORT);
        rightBackAcquirerMotor = new WPI_VictorSPX(RobotMap.ACQUIRER_BACK_RIGHT_MOTOR_PORT);
        leftFrontAcquirerMotor.setInverted(true);
        leftBackAcquirerMotor.setInverted(true);
    }
    
    public void acquire() {
        leftFrontAcquirerMotor.set(1);
        leftBackAcquirerMotor.set(1);
        rightFrontAcquirerMotor.set(1);
        rightBackAcquirerMotor.set(1);
    }
    
    public void deacquire() {
        leftFrontAcquirerMotor.set(-1);
        leftBackAcquirerMotor.set(-1);
        rightFrontAcquirerMotor.set(-1);
        rightBackAcquirerMotor.set(-1);
    }
}

