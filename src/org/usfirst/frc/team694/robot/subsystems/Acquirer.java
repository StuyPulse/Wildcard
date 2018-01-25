package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Acquirer extends Subsystem {

    public static WPI_VictorSPX leftAcquirerMotor;
    public static WPI_VictorSPX rightAcquirerMotor;
    public static Solenoid acquirerSolenoid;
        
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Acquirer() {
        leftAcquirerMotor = new WPI_VictorSPX(RobotMap.ACQUIRER_LEFT_MOTOR_PORT);
        rightAcquirerMotor = new WPI_VictorSPX(RobotMap.ACQUIRER_RIGHT_MOTOR_PORT);
        acquirerSolenoid = new Solenoid(RobotMap.ACQUIRER_SOLENOID_PORT);
        leftAcquirerMotor.setInverted(true);
    }
    
    public void acquire() {
<<<<<<< HEAD
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
=======
        leftAcquirerMotor.set(RobotMap.ACQUIRER_MOTOR_SPEED);
        rightAcquirerMotor.set(RobotMap.ACQUIRER_MOTOR_SPEED);
    }
    
    public void eject() {
        leftAcquirerMotor.set(RobotMap.ACQUIRER_MOTOR_SPEED * -1);
        rightAcquirerMotor.set(RobotMap.ACQUIRER_MOTOR_SPEED * -1);
    }
    
    public void flipUp() {
        acquirerSolenoid.set(true);
    }
    
    public void flipDown() {
        acquirerSolenoid.set(false);
>>>>>>> 04d62afa78bb5059ab3c9c7d1d22397f5866ad95
    }
}

