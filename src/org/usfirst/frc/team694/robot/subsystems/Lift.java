package org.usfirst.frc.team694.robot.subsystems;

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

    private static final int PEAK_LIMIT_AMPS = 23; // 26 is max roughly
    //    private static final int PEAK_LIMIT_MILLISECONDS = 250;

    private WPI_TalonSRX innerLeftMotor;
    private WPI_TalonSRX innerRightMotor;
    private WPI_VictorSPX outerLeftMotor;
    private WPI_VictorSPX outerRightMotor;

    private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch;

    public Lift() {
        innerLeftMotor = new WPI_TalonSRX(RobotMap.LIFT_INNER_LEFT_MOTOR_PORT);
        innerRightMotor = new WPI_TalonSRX(RobotMap.LIFT_INNER_RIGHT_MOTOR_PORT);
        //We will be using encoder data from the right motor only, and leaving it as a TalonSRX.

        outerLeftMotor = new WPI_VictorSPX(RobotMap.LIFT_OUTER_LEFT_MOTOR_PORT);
        outerRightMotor = new WPI_VictorSPX(RobotMap.LIFT_OUTER_RIGHT_MOTOR_PORT);

        /// Brake Mode
        innerLeftMotor.setNeutralMode(NeutralMode.Brake);
        innerRightMotor.setNeutralMode(NeutralMode.Brake);
        outerLeftMotor.setNeutralMode(NeutralMode.Brake);
        outerRightMotor.setNeutralMode(NeutralMode.Brake);

        innerLeftMotor.configContinuousCurrentLimit(PEAK_LIMIT_AMPS, 0);
        innerRightMotor.configContinuousCurrentLimit(PEAK_LIMIT_AMPS, 0);
        //        innerLeftMotor.configPeakCurrentDuration(PEAK_LIMIT_MILLISECONDS, 0);
        //        innerRightMotor.configPeakCurrentDuration(PEAK_LIMIT_MILLISECONDS, 0);
        innerLeftMotor.enableCurrentLimit(false);
        innerRightMotor.enableCurrentLimit(false);

        innerLeftMotor.follow(innerRightMotor);
        outerRightMotor.follow(innerRightMotor);
        outerLeftMotor.follow(innerRightMotor);

        /// Followers
        //        innerRightMotor.follow(innerLeftMotor);
        //        outerRightMotor.follow(innerLeftMotor);
        //        outerLeftMotor.follow(innerLeftMotor);

        /// Encoders
        innerLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        innerRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        // Lift P, to ramp up to a height
        //        innerLeftMotor.config_kP(0, SmartDashboard.getNumber("Lift P", 0.3), 0);
        innerRightMotor.config_kP(0, SmartDashboard.getNumber("Lift P", 0.3), 0);

        innerLeftMotor.setSensorPhase(true);
        innerRightMotor.setSensorPhase(true);

        topLimitSwitch = new DigitalInput(RobotMap.LIFT_TOP_LIMIT_SWITCH_PORT);
        bottomLimitSwitch = new DigitalInput(RobotMap.LIFT_BOTTOM_LIMIT_SWITCH_PORT);

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new LiftMoveCommand());
    }

    @Override
    public void periodic() {
        if (isAtBottom()) {
            resetEncoders();
        }
    }

    public void resetEncoders() {
        innerLeftMotor.setSelectedSensorPosition(0, 0, 0);
        innerRightMotor.setSelectedSensorPosition(0, 0, 0);
    }

    private void moveLift(double speed) {
        if ((isAtTop() && speed > 0) || (isAtBottom() && speed < 0)) {
            stop();
        } else {
            //            innerLeftMotor.set(ControlMode.PercentOutput,speed); 
            innerRightMotor.set(ControlMode.PercentOutput, speed);
        }
    }

    public void moveDangerous(double currentSpeed) {
        //        double currentHeight = getLiftHeight();
        moveLift(currentSpeed);
        //        double speed = maxSpeed;
        //                if (maxSpeed < 0) {
        //                    if (currentHeight < RobotMap.LIFT_RAMP_HEIGHT_THRESHOLD) {
        //                        speed = -(RobotMap.LIFT_RAMP_SLOPE * currentHeight + RobotMap.LIFT_MIN_SPEED);
        //                        speed = Math.max(speed, maxSpeed);
        //                    }
        //                } else {
        //                    if (currentHeight > RobotMap.LIFT_TOTAL_CARRIAGE_MOVEMENT - RobotMap.LIFT_RAMP_HEIGHT_THRESHOLD) {
        //                        speed = RobotMap.LIFT_RAMP_SLOPE * (RobotMap.LIFT_TOTAL_CARRIAGE_MOVEMENT - currentHeight) + RobotMap.LIFT_MIN_SPEED;
        //                        speed = Math.min(speed, maxSpeed);
        //                    }
        //                }

        // TODO: ADD ME BACK
        //        double speed = currentSpeed;
        //        if (currentHeight < 0) {
        //            speed = Math.max(-RobotMap.LIFT_MIN_SPEED,speed);
        //        } else if (currentSpeed < 0) {
        //            if (currentHeight < RobotMap.LIFT_RAMP_HEIGHT_THRESHOLD) {
        //                speed = -(RobotMap.LIFT_RAMP_SLOPE * currentHeight + RobotMap.LIFT_MIN_SPEED);
        //                speed = Math.max(speed, currentSpeed);
        //            }
        //        } else {
        //            if (currentHeight > RobotMap.LIFT_TOTAL_CARRIAGE_MOVEMENT - RobotMap.LIFT_RAMP_HEIGHT_THRESHOLD) {
        //                speed = RobotMap.LIFT_RAMP_SLOPE * (RobotMap.LIFT_TOTAL_CARRIAGE_MOVEMENT - currentHeight) + RobotMap.LIFT_MIN_SPEED;
        //                speed = Math.min(speed, currentSpeed);
        //            }
        //        }
        //        System.out.println("Given: " + currentSpeed + ", Actual: " + speed);
        //        moveLift(speed);
    }

    public void moveRamp(double desiredSpeed) {
        double currentHeight = getLiftHeight();
        double speed = desiredSpeed;
        if (currentHeight < 0) {
            speed = Math.max(-RobotMap.LIFT_MIN_SPEED, speed);
        } else if (desiredSpeed < 0) {
            if (currentHeight < RobotMap.LIFT_RAMP_HEIGHT_THRESHOLD) {
                speed = -(RobotMap.LIFT_RAMP_SLOPE * currentHeight + RobotMap.LIFT_MIN_SPEED);
                speed = Math.max(speed, desiredSpeed);
            }
        } else {
            if (currentHeight > RobotMap.LIFT_TOTAL_CARRIAGE_MOVEMENT - RobotMap.LIFT_RAMP_HEIGHT_THRESHOLD) {
                speed = RobotMap.LIFT_RAMP_SLOPE * (RobotMap.LIFT_TOTAL_CARRIAGE_MOVEMENT - currentHeight)
                        + RobotMap.LIFT_MIN_SPEED;
                speed = Math.min(speed, desiredSpeed);
            }
        }
        System.out.println("Given: " + desiredSpeed + ", Actual: " + speed);
        moveLift(speed);

    }

    public void setHeight(double height) {
        innerRightMotor.set(ControlMode.Position, height / RobotMap.LIFT_ENCODER_RAW_MULTIPLIER);
        //        innerLeftMotor.set(ControlMode.Position, height / RobotMap.LIFT_ENCODER_RAW_MULTIPLIER);
    }

    public void stop() {
        innerRightMotor.set(ControlMode.PercentOutput, 0);
        //        innerLeftMotor.set(ControlMode.PercentOutput, 0);
    }

    public double getSpeed() {
        return innerRightMotor.get();
        //        return innerLeftMotor.get();
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

    public double getMotorVelocity() {
        return innerRightMotor.getSelectedSensorVelocity(0);
        //        return innerLeftMotor.getSelectedSensorVelocity(0);
    }

    /*public void enableCurrentLimit() {
        innerLeftMotor.enableCurrentLimit(true);
        innerRightMotor.enableCurrentLimit(true);
    }

    public void disableCurrentLimit() {
        innerLeftMotor.enableCurrentLimit(false);
        innerRightMotor.enableCurrentLimit(false);
    }*/

    public double getCurrent() {
        return innerLeftMotor.getOutputCurrent() + innerRightMotor.getOutputCurrent()
                + outerLeftMotor.getOutputCurrent() + outerRightMotor.getOutputCurrent();
    }
    
    public void setAutonCurrentLimit() {
        innerLeftMotor.configContinuousCurrentLimit(RobotMap.LIFT_AUTON_CURRENT_LIMIT, 0);
        innerRightMotor.configContinuousCurrentLimit(RobotMap.LIFT_AUTON_CURRENT_LIMIT, 0);
        innerLeftMotor.configPeakCurrentLimit(0, 0);
        innerRightMotor.configPeakCurrentLimit(0, 0);
        innerLeftMotor.enableCurrentLimit(true);
        innerRightMotor.enableCurrentLimit(true);
    }
    
    public void setTeleopCurrentLimit() {
        innerLeftMotor.configContinuousCurrentLimit(RobotMap.LIFT_TELEOP_CURRENT_LIMIT, 0);
        innerRightMotor.configContinuousCurrentLimit(RobotMap.LIFT_TELEOP_CURRENT_LIMIT, 0);
        innerLeftMotor.configPeakCurrentLimit(0, 0);
        innerRightMotor.configPeakCurrentLimit(0, 0);
        innerLeftMotor.enableCurrentLimit(true);
        innerRightMotor.enableCurrentLimit(true);
    }
}
