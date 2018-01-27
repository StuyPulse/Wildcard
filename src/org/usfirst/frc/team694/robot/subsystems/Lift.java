package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {
    
    private WPI_TalonSRX leftLiftMotor;
    private WPI_TalonSRX rightLiftMotor; 
    
    private Solenoid liftSolenoid; 

    private boolean brakeOn; 
    
    

    public Lift() {
        
        leftLiftMotor = new WPI_TalonSRX(RobotMap.LEFT_LIFT_MOTOR_PORT);
        rightLiftMotor= new WPI_TalonSRX(RobotMap.RIGHT_LIFT_MOTOR_PORT);
        

        leftLiftMotor.setNeutralMode(NeutralMode.Brake);
        rightLiftMotor.setNeutralMode(NeutralMode.Brake);
        
        rightLiftMotor.setInverted(true);

        rightLiftMotor.follow(leftLiftMotor);
        
        leftLiftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        rightLiftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        
        liftSolenoid = new Solenoid(RobotMap.LIFT_BRAKE_SOLENOID_CHANNEL);
        
        // Configures the limit switches (forward is top, reverse is bottom)
        leftLiftMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
        leftLiftMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
        
        // Line below resets encoders when the bottom limit switch is activated
        leftLiftMotor.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0, 0);
        
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
        leftLiftMotor.set(1);
    }
    
    public void goDown() {
        leftLiftMotor.set(-1);
    }
    
    public void stop() {
        leftLiftMotor.set(0);
    }
  
    public boolean isAtBottom() {
        return leftLiftMotor.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean isAtTop() { 
       return leftLiftMotor.getSensorCollection().isFwdLimitSwitchClosed();
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