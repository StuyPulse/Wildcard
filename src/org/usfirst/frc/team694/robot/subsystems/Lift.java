package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {

    private WPI_TalonSRX innerLeftMotor;
    private WPI_TalonSRX innerRightMotor;//We don't want to use the encoders.
    private WPI_VictorSPX outerLeftMotor;
    private WPI_VictorSPX outerRightMotor;
    
    private Solenoid brakeSolenoid; 

  
    public Lift() {
        innerLeftMotor = new WPI_TalonSRX(RobotMap.INNER_LEFT_LIFT_MOTOR_PORT);
        innerRightMotor = new WPI_TalonSRX(RobotMap.INNER_RIGHT_LIFT_MOTOR_PORT);
        //We will be using encoder data from the left motor only, and leaving it as a TalonSRX.
        
        outerLeftMotor = new WPI_VictorSPX(RobotMap.OUTER_LEFT_LIFT_MOTOR_PORT);
        outerRightMotor = new WPI_VictorSPX(RobotMap.OUTER_RIGHT_LIFT_MOTOR_PORT);

        innerLeftMotor.setNeutralMode(NeutralMode.Brake);
        innerRightMotor.setNeutralMode(NeutralMode.Brake);
        
        outerLeftMotor.setNeutralMode(NeutralMode.Brake);
        outerRightMotor.setNeutralMode(NeutralMode.Brake);

        innerRightMotor.follow(innerLeftMotor);
        outerRightMotor.follow(innerLeftMotor);
        outerLeftMotor.follow(innerLeftMotor);
  
        innerLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        
        //innerRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        //We will only be using an encoder on the left motor.
        
        brakeSolenoid = new Solenoid(RobotMap.LIFT_BRAKE_SOLENOID_PORT);

        
        // Configures the limit switches (forward is top, reverse is bottom)
        innerLeftMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
        innerLeftMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
        
        // Line below resets encoders when the bottom limit switch is activated
        innerLeftMotor.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0, 0);
        
    }

    public void initDefaultCommand() {
    }

    public void resetEncoders() {
        innerLeftMotor.setSelectedSensorPosition(0, 0, 0);
    }

    public void setBrakeOn() {
        brakeSolenoid.set(true);
    }
    
    public void goUp() {
        innerLeftMotor.set(1);
    }
    
    public void goDown() {
        innerLeftMotor.set(-1);
    }

    public void setBrakeOff() {
        brakeSolenoid.set(false);
    }

    public void toggleBrake() {
        if (brakeSolenoid.get()) {
            setBrakeOff();
        } else {
            setBrakeOn();
        }
    }

    public void stop() {
        innerLeftMotor.set(0);
        setBrakeOn();
    }
    
    public boolean getBrakeStatus() {
        return brakeSolenoid.get();
    }

    public boolean isAtBottom() {
        return innerLeftMotor.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean isAtTop() { 
       return innerLeftMotor.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public double getEncoderDistance() {
        return innerLeftMotor.getSelectedSensorPosition(0) * RobotMap.LIFT_ENCODER_RAW_MULTIPLIER;
    }
    
    public double getLiftHeight() {
        return getEncoderDistance() + RobotMap.MIN_HEIGHT_OF_LIFT;
    }
}