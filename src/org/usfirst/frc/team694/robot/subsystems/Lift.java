package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.Robot;
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

/**
 *
 */
public class Lift extends Subsystem {

    private WPI_TalonSRX innerLeftLiftMotor;
    private WPI_TalonSRX innerRightLiftMotor;
    private WPI_VictorSPX outerLeftLiftMotor;
    private WPI_VictorSPX outerRightLiftMotor;
    
    private Solenoid liftSolenoid; 

    private boolean brakeOn; 
  
    public Lift() {
        innerLeftLiftMotor = new WPI_TalonSRX(RobotMap.INNER_LEFT_LIFT_MOTOR_PORT);
        innerRightLiftMotor = new WPI_TalonSRX(RobotMap.INNER_RIGHT_LIFT_MOTOR_PORT);
        
        outerLeftLiftMotor = new WPI_VictorSPX(RobotMap.OUTER_LEFT_LIFT_MOTOR_PORT);
        outerRightLiftMotor = new WPI_VictorSPX(RobotMap.OUTER_RIGHT_LIFT_MOTOR_PORT);

        innerLeftLiftMotor.setNeutralMode(NeutralMode.Brake);
        innerRightLiftMotor.setNeutralMode(NeutralMode.Brake);
        
        outerLeftLiftMotor.setNeutralMode(NeutralMode.Brake);
        outerRightLiftMotor.setNeutralMode(NeutralMode.Brake);


        innerRightLiftMotor.follow(innerLeftLiftMotor);
        outerRightLiftMotor.follow(innerLeftLiftMotor);
        outerLeftLiftMotor.follow(innerLeftLiftMotor);
  
        innerLeftLiftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        innerRightLiftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        liftSolenoid = new Solenoid(RobotMap.LIFT_BRAKE_SOLENOID_CHANNEL);
        
        // Configures the limit switches (forward is top, reverse is bottom)
        innerLeftLiftMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
        innerLeftLiftMotor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
        
        // Line below resets encoders when the bottom limit switch is activated
        innerLeftLiftMotor.configSetParameter(ParamEnum.eClearPositionOnLimitR, 1, 0, 0, 0);
        
    }

    public void initDefaultCommand() {
    }

    public void resetEncoders() {
        innerLeftLiftMotor.setSelectedSensorPosition(0, 0, 0);
        innerRightLiftMotor.setSelectedSensorPosition(0, 0, 0);
    }

    public void setBrakeOn() {
        brakeOn = true;
        liftSolenoid.set(brakeOn);
    }
    
    public void goUp() {
        innerLeftLiftMotor.set(1);
    }
    
    public void goDown() {
        innerLeftLiftMotor.set(-1);
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

    public void stop() {
        innerLeftLiftMotor.set(0);
    }

    public boolean isAtBottom() {
        return innerLeftLiftMotor.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean isAtTop() { 
       return innerLeftLiftMotor.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public double getLeftLiftEncoderDistance() {
        return innerLeftLiftMotor.getSelectedSensorPosition(0) * RobotMap.LIFT_ENCODER_RAW_MULTIPLIER;
    }

    public double getRightLiftEncoderDistance() {
        return innerRightLiftMotor.getSelectedSensorPosition(0) * RobotMap.LIFT_ENCODER_RAW_MULTIPLIER;
    }

    public double getMaxLiftEncoderDistance() {
        return Math.max(getLeftLiftEncoderDistance(), getRightLiftEncoderDistance());
    }
    
    public double getLiftHeight() {
        return getMaxLiftEncoderDistance() + RobotMap.MIN_HEIGHT_OF_GRABBER;
    }
}