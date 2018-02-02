package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Acquirer extends Subsystem {

    public WPI_VictorSPX leftAcquirerMotor;
    public WPI_VictorSPX rightAcquirerMotor;
    public Solenoid acquirerFlipSolenoid;
    public Solenoid acquirerSqueezeSolenoid; 
    public SpeedControllerGroup acquirerMotors;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public Acquirer() {
        leftAcquirerMotor = new WPI_VictorSPX(RobotMap.ACQUIRER_LEFT_MOTOR_PORT);
        rightAcquirerMotor = new WPI_VictorSPX(RobotMap.ACQUIRER_RIGHT_MOTOR_PORT);
        acquirerFlipSolenoid = new Solenoid(RobotMap.ACQUIRER_FLIP_SOLENOID_PORT);
        acquirerSqueezeSolenoid = new Solenoid(RobotMap.ACQUIRER_SQUEEZE_SOLENOID_PORT);
        acquirerMotors = new SpeedControllerGroup(leftAcquirerMotor, rightAcquirerMotor);
    }

    public void acquire() {
        acquirerMotors.set(1);
    }

    public void deacquire() {
        acquirerMotors.set(-1);
    }

    public void flipUp() {
        acquirerFlipSolenoid.set(true);
    }

    public void flipDown() {
        acquirerFlipSolenoid.set(false);
    }
    
    public void tightenCubeGrip() {
        acquirerSqueezeSolenoid.set(true);
    }
    
    public void loosenCubeGrip() {
        acquirerSqueezeSolenoid.set(false);
    }
}
