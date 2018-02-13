package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.LiftMoveCommand;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {

    private WPI_TalonSRX innerLeftMotor;
    private WPI_TalonSRX innerRightMotor;
    private WPI_VictorSPX outerLeftMotor;
    private WPI_VictorSPX outerRightMotor;

    //    private Solenoid brakeSolenoid;

    //private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch;

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

        //innerRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        //We will only be using an encoder on the left motor.

        //        brakeSolenoid = new Solenoid(RobotMap.LIFT_BRAKE_SOLENOID_PORT);

        //topLimitSwitch = new DigitalInput(RobotMap.LIFT_TOP_LIMIT_SWITCH_PORT);
        bottomLimitSwitch = new DigitalInput(RobotMap.LIFT_BOTTOM_LIMIT_SWITCH_PORT);

    }

    public void initDefaultCommand() {
        setDefaultCommand(new LiftMoveCommand());
    }

    public void periodic() {
        if (isAtBottom()) {
            resetEncoders();
        }
    }

    public void resetEncoders() {
        innerLeftMotor.setSelectedSensorPosition(0, 0, 0);
        innerRightMotor.setSelectedSensorPosition(0, 0, 0);
    }

    public void setBrakeOn() {
        //        brakeSolenoid.set(true);
    }

    public void setBrakeOff() {
        //        brakeSolenoid.set(false);
    }

    public void toggleBrake() {
        //        if (brakeSolenoid.get()) {
        //            setBrakeOff();
        //        } else {
        //            setBrakeOn();
        //        }
    }

    private void moveLift(double speed) {
        setBrakeOff();
        //TODO: Conjoin if statements
        
        if ((isAtTop() && speed > 0) || (isAtBottom() && speed < 0)) {
            stop();
        } else {
            innerLeftMotor.set(speed);
        }
        //        if(isAtBottom()) {
        //            stop();
        //        } else {
        //            innerLeftMotor.set(speed);
        //        }
    }

    public void move(double maxSpeed) {
        double currentHeight = getLiftHeight();
        double speed = maxSpeed;
        //        if (maxSpeed < 0) {
        //            if (currentHeight < RobotMap.LIFT_HEIGHT_THRESHOLD) {
        //                speed = -RobotMap.LIFT_RAMP_SLOPE * currentHeight + RobotMap.LIFT_MIN_SPEED;
        //            }
        //        } else {
        //            if (currentHeight > RobotMap.LIFT_TOTAL_CARRIAGE_MOVEMENT - RobotMap.LIFT_HEIGHT_THRESHOLD) {
        //                speed = RobotMap.LIFT_RAMP_SLOPE * (RobotMap.LIFT_TOTAL_CARRIAGE_MOVEMENT - currentHeight) + RobotMap.LIFT_MIN_SPEED;
        //            }
        //        }
        moveLift(speed);
    }

    public double getSpeed() {
        return innerLeftMotor.get();
    }

    public void stop() {
        innerLeftMotor.set(0);
        setBrakeOn();
    }

    public boolean getBrakeStatus() {
        return false;
        //        return brakeSolenoid.get();
    }

    public boolean isAtBottom() {
        return !bottomLimitSwitch.get();
    }

    public boolean isAtTop() {
        return false; //topLimitSwitch.get();
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
}