package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.LiftMoveCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lift extends Subsystem {

    private WPI_TalonSRX innerLeftMotor;
    private WPI_TalonSRX innerRightMotor;
    private WPI_VictorSPX outerLeftMotor;
    private WPI_VictorSPX outerRightMotor;

    private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch;
    
    //private boolean wasWildcard = true;

    public Lift() {
        innerLeftMotor = new WPI_TalonSRX(RobotMap.LIFT_INNER_LEFT_MOTOR_PORT);
        innerRightMotor = new WPI_TalonSRX(RobotMap.LIFT_INNER_RIGHT_MOTOR_PORT);
        //We will be using encoder data from the left motor only, and leaving it as a TalonSRX.

        outerLeftMotor = new WPI_VictorSPX(RobotMap.LIFT_OUTER_LEFT_MOTOR_PORT);
        outerRightMotor = new WPI_VictorSPX(RobotMap.LIFT_OUTER_RIGHT_MOTOR_PORT);

        innerLeftMotor.setNeutralMode(NeutralMode.Brake);
        innerRightMotor.setNeutralMode(NeutralMode.Brake);

        outerLeftMotor.setNeutralMode(NeutralMode.Brake);
        outerRightMotor.setNeutralMode(NeutralMode.Brake);

        innerRightMotor.follow(innerLeftMotor);
        outerRightMotor.follow(innerLeftMotor);
        outerLeftMotor.follow(innerLeftMotor);

        innerLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        innerRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        innerLeftMotor.config_kP(0, SmartDashboard.getNumber("Lift P", 0.3), 0);

        innerLeftMotor.setSensorPhase(Robot.IS_WILDCARD);
        innerRightMotor.setSensorPhase(Robot.IS_WILDCARD);

        topLimitSwitch = new DigitalInput(RobotMap.LIFT_TOP_LIMIT_SWITCH_PORT);
        bottomLimitSwitch = new DigitalInput(RobotMap.LIFT_BOTTOM_LIMIT_SWITCH_PORT);

    }

    public void initDefaultCommand() {
        setDefaultCommand(new LiftMoveCommand());
    }

    public void periodic() {
        if (isAtBottom()) {
            resetEncoders();
        }
//        if(SmartDashboard.getBoolean("Is Wildcard?", true) != wasWildcard) {
//            wasWildcard = SmartDashboard.getBoolean("Is Wildcard?", true);
//            System.out.println("CHANGED "+ wasWildcard);
//            innerLeftMotor.setInverted(!wasWildcard);
//            innerRightMotor.setInverted(!wasWildcard);
//            outerLeftMotor.setInverted(!wasWildcard);
//            outerLeftMotor.setInverted(!wasWildcard);
//        }
    }

    public void resetEncoders() {
        innerLeftMotor.setSelectedSensorPosition(0, 0, 0);
        innerRightMotor.setSelectedSensorPosition(0, 0, 0);
    }

    public void moveLift(double speed) {        
        if ((isAtTop() && speed > 0) || (isAtBottom() && speed < 0)) {
            stop();
        } else {
            if(!Robot.IS_WILDCARD) {
                speed = -speed;
            }
            innerLeftMotor.set(ControlMode.PercentOutput,speed); 
        }
    }

    public void moveLiftRamping(double currentSpeed) {
        double currentHeight = getLiftHeight();
        double speed = currentSpeed;
        if (currentHeight < 0) {
            speed = Math.max(-RobotMap.LIFT_MIN_SPEED,speed);
        } else if  (currentHeight > RobotMap.LIFT_MAX_HEIGHT){
            speed = Math.min(RobotMap.LIFT_MIN_SPEED, speed);
        } else if (currentSpeed < 0) {
            if (currentHeight < RobotMap.LIFT_RAMP_HEIGHT_THRESHOLD) {
                speed = -(RobotMap.LIFT_RAMP_SLOPE * currentHeight + RobotMap.LIFT_MIN_SPEED);
                speed = Math.max(speed, currentSpeed);
            }
        } else {
            if (currentHeight > RobotMap.LIFT_TOTAL_CARRIAGE_MOVEMENT - RobotMap.LIFT_RAMP_HEIGHT_THRESHOLD) {
                speed = RobotMap.LIFT_RAMP_SLOPE * (RobotMap.LIFT_TOTAL_CARRIAGE_MOVEMENT - currentHeight) + RobotMap.LIFT_MIN_SPEED;
                speed = Math.min(speed, currentSpeed);
            }
        }

//        System.out.println("Given: " + currentSpeed + ", Actual: " + speed);
        moveLift(speed);
    }

    public double getSpeed() {
        return innerLeftMotor.get();
    }

    public void stop() {
        innerLeftMotor.set(ControlMode.PercentOutput, 0);
    }

    public boolean getBrakeStatus() {
        return false;
        //        return brakeSolenoid.get();
    }

    public boolean isAtBottom() {
        return !bottomLimitSwitch.get();
    }

    public boolean isAtTop() {
        return !topLimitSwitch.get();
    }

    public double getLeftRawEncoderDistance() {
        return innerLeftMotor.getSelectedSensorPosition(0);
    }

    public double getRightRawEncoderDistance() {
        return innerRightMotor.getSelectedSensorPosition(0);
    }

    public double getLeftEncoderDistance() {
        return innerLeftMotor.getSelectedSensorPosition(0) * RobotMap.LIFT_ENCODER_RAW_MULTIPLIER;
    }

    public double getRightEncoderDistance() {
        return innerRightMotor.getSelectedSensorPosition(0) * RobotMap.LIFT_ENCODER_RAW_MULTIPLIER;
    }

    public double getLiftHeight() {
        return Math.max(getLeftEncoderDistance(), getRightEncoderDistance());
    }
    
    public void setHeight(double height) {
        innerLeftMotor.set(ControlMode.Position, height / RobotMap.LIFT_ENCODER_RAW_MULTIPLIER);
    }
    
    public double getMotorVelocity() {
        return innerLeftMotor.getSelectedSensorVelocity(0);
    }
}
