/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.            */
/* Open Source Software - may be modified and shared by FRC teams. The code  */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.DrivetrainDriveSystemCommand;
import org.usfirst.frc.team694.util.LineSensor;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem {

    // TODO: Set
//    private double LIFT_AND_DRIVETRAIN_CURRENT_LIMIT = 999999;
//    private int DRIVETRAIN_CURRENT_LIMIT_LIFT = 10;
    private static final double BROWNOUT_PROTECTION_PVBUS_CAP = 0.9;

    private WPI_VictorSPX leftTopMotor;
    private WPI_VictorSPX leftMiddleMotor;
    private WPI_TalonSRX leftBottomMotor;
    private WPI_VictorSPX rightTopMotor;
    private WPI_VictorSPX rightMiddleMotor;
    private WPI_TalonSRX rightBottomMotor;

    private DifferentialDrive differentialDrive;

    private LineSensor leftLineSensor;
    private LineSensor rightLineSensor;

    private Solenoid gearShift;

    public static AHRS navX;

    private double absoluteGyroError;

    private boolean brownoutProtectionEnabled;

//    private boolean brownoutProtectionEnabled;

    public Drivetrain() {
        /// Left Motors
        leftTopMotor = new WPI_VictorSPX(RobotMap.DRIVETRAIN_LEFT_TOP_MOTOR_PORT);
        leftMiddleMotor = new WPI_VictorSPX(RobotMap.DRIVETRAIN_LEFT_MIDDLE_MOTOR_PORT);
        leftBottomMotor = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_BOTTOM_MOTOR_PORT);

        /// Right Motors
        rightTopMotor = new WPI_VictorSPX(RobotMap.DRIVETRAIN_RIGHT_TOP_MOTOR_PORT);
        rightMiddleMotor = new WPI_VictorSPX(RobotMap.DRIVETRAIN_RIGHT_MIDDLE_MOTOR_PORT);
        rightBottomMotor = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_BOTTOM_MOTOR_PORT);

        // Followers
        leftMiddleMotor.follow(leftBottomMotor);
        leftTopMotor.follow(leftBottomMotor);
        rightMiddleMotor.follow(rightBottomMotor);
        rightTopMotor.follow(rightBottomMotor);

        // Current limit
//        leftBottomMotor.configContinuousCurrentLimit(DRIVETRAIN_CURRENT_LIMIT_LIFT, 0);
//        rightBottomMotor.configContinuousCurrentLimit(DRIVETRAIN_CURRENT_LIMIT_LIFT, 0);
//        leftBottomMotor.configPeakCurrentLimit(DRIVETRAIN_CURRENT_LIMIT_LIFT, 0);
//        rightBottomMotor.configPeakCurrentLimit(DRIVETRAIN_CURRENT_LIMIT_LIFT, 0);
//        leftBottomMotor.configPeakCurrentDuration(1, 0);
//        rightBottomMotor.configPeakCurrentDuration(1, 0);
//        leftBottomMotor.enableCurrentLimit(false);
//        rightBottomMotor.enableCurrentLimit(false);

        /// Inverted
        rightTopMotor.setInverted(true);
        rightMiddleMotor.setInverted(true);
        rightBottomMotor.setInverted(true);
        leftTopMotor.setInverted(true);
        leftMiddleMotor.setInverted(true);
        leftBottomMotor.setInverted(true);

        /// Brake Mode
        leftTopMotor.setNeutralMode(NeutralMode.Brake);
        leftMiddleMotor.setNeutralMode(NeutralMode.Brake);
        leftBottomMotor.setNeutralMode(NeutralMode.Brake);
        rightTopMotor.setNeutralMode(NeutralMode.Brake);
        rightMiddleMotor.setNeutralMode(NeutralMode.Brake);
        rightBottomMotor.setNeutralMode(NeutralMode.Brake);

        /// Encoders
        leftBottomMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        rightBottomMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        differentialDrive = new DifferentialDrive(leftBottomMotor, rightBottomMotor);

        /// Line Sensors
        leftLineSensor = new LineSensor(RobotMap.DRIVETRAIN_LINE_SENSOR_LEFT_PORT);
        rightLineSensor = new LineSensor(RobotMap.DRIVETRAIN_LINE_SENSOR_RIGHT_PORT);

        navX = new AHRS(SPI.Port.kMXP);

        gearShift = new Solenoid(RobotMap.GEAR_SHIFT_CHANNEL);
    }

    @Override
    public void initDefaultCommand() {
        //setDefaultCommand(new DriveCommand());
        setDefaultCommand(new DrivetrainDriveSystemCommand());
    }

    //@Override
    public void periodic(){
//        System.out.println("[Drivetrain] LEFT -> mid:" + leftMiddleMotor.getOutputCurrent());
//        SmartDashboard.putNumber("[Drivetrain] left bottom motor current", leftBottomMotor.getOutputCurrent());
////        updateSensors();
//        // TODO: Test with current limit set 100% of the time to find optimal current limit
//        // TODO: Find at what point should the lift be limited
//        if (getCurrent() + Robot.lift.getCurrent() > LIFT_AND_DRIVETRAIN_CURRENT_LIMIT /* SET ME */) {
//            enableCurrentLimit();
//        } else {
//            disableCurrentLimit();
//        }
    }

    public double getLeftSpeed() {
        return leftBottomMotor.getSelectedSensorVelocity(0);
    }

    public double getRightSpeed() {
        return rightBottomMotor.getSelectedSensorVelocity(0);
    }

    public double getSpeed() {
        return Math.max(Math.abs(getLeftSpeed()), Math.abs(getRightSpeed()));
    }

    public double getEncoderDistance() {
        return Math.abs(getEncoderMax());
    }

    // Gets the encoder with the largest magnitude
    // Generally put in place to avoid "one encoder reads 0 100% of the time" problems
    public double getEncoderMax() {
        double left = getLeftEncoderDistance();
        double right = getRightEncoderDistance();

        if (Math.abs(left) > Math.abs(right)) {
            return left;
        } else {
            return right;
        }
    }

    public double getRawEncoderDistance() {
        return Math.abs(Math.max(getLeftRawEncoderDistance(), getRightRawEncoderDistance()));
    }

    public double getLeftEncoderDistance() {
        return leftBottomMotor.getSelectedSensorPosition(0) * RobotMap.DRIVETRAIN_RAW_MULTIPLIER;
    }

    public double getRightEncoderDistance() {
        return -1 * rightBottomMotor.getSelectedSensorPosition(0) * RobotMap.DRIVETRAIN_RAW_MULTIPLIER;
    }

    public double getLeftRawEncoderDistance() {
        return leftBottomMotor.getSelectedSensorPosition(0);
    }

    public double getRightRawEncoderDistance() {
        return -1 * rightBottomMotor.getSelectedSensorPosition(0);
    }

    public void resetEncoders() {
        leftBottomMotor.setSelectedSensorPosition(0, 0, 100);
        rightBottomMotor.setSelectedSensorPosition(0, 0, 100);
    }

    public void setEncoders(double inches) {
        leftBottomMotor.setSelectedSensorPosition(
                (int) (getLeftRawEncoderDistance() + (inches / RobotMap.DRIVETRAIN_RAW_MULTIPLIER)), 0, 0);
        rightBottomMotor.setSelectedSensorPosition(
                (int) (getRightRawEncoderDistance() + (inches / RobotMap.DRIVETRAIN_RAW_MULTIPLIER)), 0, 0);
    }

    public void tankDrive(double left, double right) {
        if (brownoutProtectionEnabled) {
            left = Math.signum(left) * Math.min(Math.abs(left),BROWNOUT_PROTECTION_PVBUS_CAP);
            right = Math.signum(right) * Math.min(Math.abs(right),BROWNOUT_PROTECTION_PVBUS_CAP);
        }
        differentialDrive.tankDrive(left, right, false);
    }

    public void arcadeDrive(double speed, double rotation) {
        if (brownoutProtectionEnabled) {
            speed = Math.signum(speed) * Math.min(Math.abs(speed),BROWNOUT_PROTECTION_PVBUS_CAP);
            rotation = Math.signum(rotation) * Math.min(Math.abs(rotation),BROWNOUT_PROTECTION_PVBUS_CAP);
        }
        differentialDrive.arcadeDrive(speed, rotation);
    }

    public void curvatureDrive(double speed, double rotation, boolean turn) {
        if (brownoutProtectionEnabled) {
            speed = Math.signum(speed) * Math.min(Math.abs(speed),BROWNOUT_PROTECTION_PVBUS_CAP);
            rotation = Math.signum(rotation) * Math.min(Math.abs(rotation),BROWNOUT_PROTECTION_PVBUS_CAP);
        }
        differentialDrive.curvatureDrive(speed, rotation, turn);
    }

    public void stop() {
        tankDrive(0, 0);
    }

    public void highGearShift() {
        gearShift.set(false);
    }

    public void lowGearShift() {
        gearShift.set(true);
    }

    public void toggleGearShift() {
        boolean m = !(gearShift.get());
        gearShift.set(m);
    }

    public void setGearShift(boolean isShifted) {
        gearShift.set(isShifted);
    }

    public boolean isGearShift() {
        return gearShift.get();
    }

    //    public void resetLineSensors(){
    //        leftLineSensor.resetAmbient();
    //        rightLineSensor.resetAmbient();
    //    }

    public double getGyroAngle() {
        return navX.getAngle();
    }

    public void updateSensors() {
        rightLineSensor.mainLoop();
        leftLineSensor.mainLoop();
    }

    public boolean isOnLine() {//TODO:Decide if we want to have different auton speeds(modes). If so, then create enums instead.
        return leftIsOnLine() || rightIsOnLine();
    }

    public boolean rightIsOnLine() {
        return rightLineSensor.basicFind();
    }

    public boolean leftIsOnLine() {
        return leftLineSensor.basicFind();
    }

    public double getRawLeftLineSensor() {
        return leftLineSensor.getRawData();
    }

    public double getRawRightLineSensor() {
        return rightLineSensor.getRawData();
    }

    public void setRamp(double rampSeconds) {
        leftTopMotor.configOpenloopRamp(rampSeconds, 0);
        rightTopMotor.configOpenloopRamp(rampSeconds, 0);
        leftMiddleMotor.configOpenloopRamp(rampSeconds, 0);
        rightMiddleMotor.configOpenloopRamp(rampSeconds, 0);
        leftBottomMotor.configOpenloopRamp(rampSeconds, 0);
        rightBottomMotor.configOpenloopRamp(rampSeconds, 0);
    }

    public void resetGyro() {
        absoluteGyroError += getGyroAngle();
        navX.reset();
    }

    public void resetGyroError() {
        absoluteGyroError = 0;
    }

    // If anyone mentions the phrase "current limit",
    // they will be given a angry look
    public void enableBrownOutProtection() {
//        System.out.println("[Drivetrain] ENABLE brownout protection");
        brownoutProtectionEnabled = true;
//        leftBottomMotor.enableCurrentLimit(true);
//        rightBottomMotor.enableCurrentLimit(true);
    }

    public void disableBrownOutProtection() {
//        System.out.println("[Drivetrain] DISABLE brownout protection");
        brownoutProtectionEnabled = false;
//        leftBottomMotor.enableCurrentLimit(false);
//        rightBottomMotor.enableCurrentLimit(false);
    }

    public double getCurrent() {
        return leftBottomMotor.getOutputCurrent() 
             + rightBottomMotor.getOutputCurrent()
             + leftMiddleMotor.getOutputCurrent()
             + rightMiddleMotor.getOutputCurrent()
             + leftTopMotor.getOutputCurrent()
             + rightTopMotor.getOutputCurrent();
    }

    public double getAbsoluteGyroAngle() {
        return absoluteGyroError + getGyroAngle();
    }
}

