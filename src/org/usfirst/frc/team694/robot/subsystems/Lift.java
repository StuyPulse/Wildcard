package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {
    
    private WPI_TalonSRX leftLiftMotor;
    private WPI_TalonSRX rightLiftMotor;
    
    private Solenoid liftSolenoid; 

    private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch;

    private static boolean brakeOn; 

    public Lift() {
        
        leftLiftMotor = new WPI_TalonSRX(RobotMap.LEFT_LIFT_MOTOR_PORT);
        rightLiftMotor= new WPI_TalonSRX(RobotMap.RIGHT_LIFT_MOTOR_PORT);


        leftLiftMotor.setNeutralMode(NeutralMode.Brake);
        rightLiftMotor.setNeutralMode(NeutralMode.Brake);
        
        rightLiftMotor.setInverted(true);

        leftLiftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        rightLiftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        
        liftSolenoid = new Solenoid(RobotMap.LIFT_BRAKE_SOLENOID_CHANNEL);
    }

    public void initDefaultCommand() {
    }

    public void resetEncoders() {
        leftLiftMotor.setSelectedSensorPosition(0, 0, 0);
        rightLiftMotor.setSelectedSensorPosition(0, 0, 0);
    }

    public void setBrakeOn() {
        brakeOn = true; 
        liftSolenoid.set(brakeOn);
    }

    public void setBrakeOff() {
        brakeOn = false; 
        liftSolenoid.set(brakeOn);
    }
    
    public void toggleBrake() {
        if (brakeOn) {
            setBrakeOff();
        } else {
            setBrakeOn();
        }
    }
    
    public void goUp() {
        rightLiftMotor.set(1.0);
        leftLiftMotor.set(1.0);
    }
    
    public void goDown() {
        rightLiftMotor.set(-1.0);
        leftLiftMotor.set(-1.0);
    }
    
    public void stop() {
        rightLiftMotor.set(0);
        leftLiftMotor.set(0);
    }
  
    public boolean isAtBottom() {
        return bottomLimitSwitch.get();
    }

    public boolean isAtTop() { 
       return topLimitSwitch.get();
    }
    
    public double getLeftLiftEncoderDistance() {
        return leftLiftMotor.getSelectedSensorPosition(0) * RobotMap.LIFT_RAW_MULTIPLIER;
    }
    
    public double getRightLiftEncoderDistance() {
        return rightLiftMotor.getSelectedSensorPosition(0) * RobotMap.LIFT_RAW_MULTIPLIER;
    }
    
    public double getMaxLiftEncoderDistance() {
        return Math.max(getLeftLiftEncoderDistance(), getRightLiftEncoderDistance());  
    }
    
}