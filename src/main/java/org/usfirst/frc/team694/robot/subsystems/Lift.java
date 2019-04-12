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

    private static final int PEAK_LIMIT_AMPS = 23 + 999; // 26 is max roughly
    //    private static final int PEAK_LIMIT_MILLISECONDS = 250;

    private WPI_TalonSRX followerSideTalon;
    private WPI_TalonSRX masterSideTalon;
    private WPI_VictorSPX leftSideVictor;
    private WPI_VictorSPX rightSideVictor;

    private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch;
    
    public static boolean rampDisabled;
    
    private boolean isOverridingLimitSwitch;

    public Lift() {
        followerSideTalon = new WPI_TalonSRX(RobotMap.LIFT_INNER_RIGHT_MOTOR_PORT);
        masterSideTalon = new WPI_TalonSRX(RobotMap.LIFT_INNER_LEFT_MOTOR_PORT);
        //We will be using encoder data from the left motor only, and leaving it as a TalonSRX.

        leftSideVictor = new WPI_VictorSPX(RobotMap.LIFT_OUTER_LEFT_MOTOR_PORT);
        rightSideVictor = new WPI_VictorSPX(RobotMap.LIFT_OUTER_RIGHT_MOTOR_PORT);

        /// Brake Mode
        followerSideTalon.setNeutralMode(NeutralMode.Brake);
        masterSideTalon.setNeutralMode(NeutralMode.Brake);
        leftSideVictor.setNeutralMode(NeutralMode.Brake);
        rightSideVictor.setNeutralMode(NeutralMode.Brake);

        followerSideTalon.configContinuousCurrentLimit(PEAK_LIMIT_AMPS, 0);
        masterSideTalon.configContinuousCurrentLimit(PEAK_LIMIT_AMPS, 0);
        //        innerLeftMotor.configPeakCurrentDuration(PEAK_LIMIT_MILLISECONDS, 0);
        //        innerRightMotor.configPeakCurrentDuration(PEAK_LIMIT_MILLISECONDS, 0);
        followerSideTalon.enableCurrentLimit(false);
        masterSideTalon.enableCurrentLimit(false);

        followerSideTalon.follow(masterSideTalon);
        rightSideVictor.follow(masterSideTalon);
        leftSideVictor.follow(masterSideTalon);

        /// Followers
        //        innerRightMotor.follow(innerLeftMotor);
        //        outerRightMotor.follow(innerLeftMotor);
        //        outerLeftMotor.follow(innerLeftMotor);

        /// Encoders
        followerSideTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        masterSideTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        // Lift P, to ramp up to a height
        //        innerLeftMotor.config_kP(0, SmartDashboard.getNumber("Lift P", 0.3), 0);
        masterSideTalon.config_kP(0, SmartDashboard.getNumber("Lift P", 0.3), 0);

        if (Robot.IS_MILDCARD) {
            followerSideTalon.setSensorPhase(true);
            masterSideTalon.setSensorPhase(true);
        } else {
            followerSideTalon.setSensorPhase(false);
            masterSideTalon.setSensorPhase(false);
        }
        topLimitSwitch = new DigitalInput(RobotMap.LIFT_TOP_LIMIT_SWITCH_PORT);
        bottomLimitSwitch = new DigitalInput(RobotMap.LIFT_BOTTOM_LIMIT_SWITCH_PORT);

        enableRamping();
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
        followerSideTalon.setSelectedSensorPosition(0, 0, 0);
        masterSideTalon.setSelectedSensorPosition(0, 0, 0);
    }

    private void moveLift(double speed) {
        if (!isOverridingLimitSwitch && ((isAtTop() && speed > 0) || (isAtBottom() && speed < 0))) {
            stop();
        } else {
            //            innerLeftMotor.set(ControlMode.PercentOutput,speed); 
            masterSideTalon.set(ControlMode.PercentOutput, speed);
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
        masterSideTalon.set(ControlMode.Position, height / RobotMap.LIFT_ENCODER_RAW_MULTIPLIER);
        //        innerLeftMotor.set(ControlMode.Position, height / RobotMap.LIFT_ENCODER_RAW_MULTIPLIER);
    }

    public void stop() {
        masterSideTalon.set(ControlMode.PercentOutput, 0);
        //        innerLeftMotor.set(ControlMode.PercentOutput, 0);
    }

    public double getSpeed() {
        return masterSideTalon.get();
        //        return innerLeftMotor.get();
    }

    public boolean isAtBottom() {
        return !bottomLimitSwitch.get();
    }

    public boolean isAtTop() {
        return !topLimitSwitch.get();
    }

    public double getFollowerRawEncoderDistance() {
        return followerSideTalon.getSelectedSensorPosition(0);
    }

    public double getMasterRawEncoderDistance() {
        return masterSideTalon.getSelectedSensorPosition(0);
    }

    public double getFollowerEncoderDistance() {
        return followerSideTalon.getSelectedSensorPosition(0) * RobotMap.LIFT_ENCODER_RAW_MULTIPLIER;
    }

    public double getMasterEncoderDistance() {
        return masterSideTalon.getSelectedSensorPosition(0) * RobotMap.LIFT_ENCODER_RAW_MULTIPLIER;
    }

    public double getLiftHeight() {
        return Math.max(getFollowerEncoderDistance(), getMasterEncoderDistance());
    }

    public double getMotorVelocity() {
        return masterSideTalon.getSelectedSensorVelocity(0);
        //       return innerLeftMotor.getSelectedSensorVelocity(0);
    }

    public void enableCurrentLimit() {
        followerSideTalon.enableCurrentLimit(true);
        masterSideTalon.enableCurrentLimit(true);
    }

    public void enableRamping() {
        rampDisabled = false;
        if (Robot.isInTeleop()) {
            masterSideTalon.configOpenloopRamp(0.5, 0);
        } else {
            masterSideTalon.configOpenloopRamp(0.2, 0);
        }
    }

    public void disableRamping() {
        masterSideTalon.configOpenloopRamp(0.2, 0);
        rampDisabled = true;
        masterSideTalon.configOpenloopRamp(0, 0);
    }

    public void disableCurrentLimit() {
        followerSideTalon.enableCurrentLimit(false);
        masterSideTalon.enableCurrentLimit(false);
    }

    public void disableLoopRamping() {
        rampDisabled = false;
        masterSideTalon.configOpenloopRamp(0.2, 0);
    }

    public void disableAllRamping() {
        rampDisabled = true;
        masterSideTalon.configOpenloopRamp(0, 0);
    }
    public void enableOverrideLimitSwitch() {
        isOverridingLimitSwitch = true;
    }

    public void disableOverrideLimitSwitch() {
        isOverridingLimitSwitch = false;
    }

    // public double getCurrent() {
    //     return followerSideTalon.getOutputCurrent() + masterSideTalon.getOutputCurrent()
    //             + leftSideVictor.getOutputCurrent() + rightSideVictor.getOutputCurrent();
    // }
}
