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

    private static final int PEAK_LIMIT_AMPS = 4;
    private static final int PEAK_LIMIT_MILLISECONDS = 250;

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
    
    private double absoluteGyro;

    public Drivetrain() {
        leftTopMotor = new WPI_VictorSPX(RobotMap.DRIVETRAIN_LEFT_TOP_MOTOR_PORT);
        leftMiddleMotor = new WPI_VictorSPX(RobotMap.DRIVETRAIN_LEFT_MIDDLE_MOTOR_PORT);
        leftBottomMotor = new WPI_TalonSRX(RobotMap.DRIVETRAIN_LEFT_BOTTOM_MOTOR_PORT);
        //master-follower, leftBottomMotor designated master
        leftMiddleMotor.follow(leftBottomMotor);
        leftTopMotor.follow(leftBottomMotor);

        rightTopMotor = new WPI_VictorSPX(RobotMap.DRIVETRAIN_RIGHT_TOP_MOTOR_PORT);
        rightMiddleMotor = new WPI_VictorSPX(RobotMap.DRIVETRAIN_RIGHT_MIDDLE_MOTOR_PORT);
        rightBottomMotor = new WPI_TalonSRX(RobotMap.DRIVETRAIN_RIGHT_BOTTOM_MOTOR_PORT);
        //master-follower, rightBottomMotor designated master
        rightMiddleMotor.follow(rightBottomMotor);
        rightTopMotor.follow(rightBottomMotor);

        rightTopMotor.setInverted(true);
        rightMiddleMotor.setInverted(true);
        rightBottomMotor.setInverted(true);
        leftTopMotor.setInverted(true);
        leftMiddleMotor.setInverted(true);
        leftBottomMotor.setInverted(true);

        leftTopMotor.setNeutralMode(NeutralMode.Brake);
        leftMiddleMotor.setNeutralMode(NeutralMode.Brake);
        leftBottomMotor.setNeutralMode(NeutralMode.Brake);
        rightTopMotor.setNeutralMode(NeutralMode.Brake);
        rightMiddleMotor.setNeutralMode(NeutralMode.Brake);
        rightBottomMotor.setNeutralMode(NeutralMode.Brake);

        leftBottomMotor.configPeakCurrentLimit(PEAK_LIMIT_AMPS, 0);
        rightBottomMotor.configPeakCurrentLimit(PEAK_LIMIT_AMPS, 0);
        leftBottomMotor.configPeakCurrentDuration(PEAK_LIMIT_MILLISECONDS, 0);
        rightBottomMotor.configPeakCurrentDuration(PEAK_LIMIT_MILLISECONDS, 0);
        leftBottomMotor.enableCurrentLimit(false);
        rightBottomMotor.enableCurrentLimit(false);

        leftBottomMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        rightBottomMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        leftLineSensor = new LineSensor(RobotMap.DRIVETRAIN_LINE_SENSOR_LEFT_PORT);
        rightLineSensor = new LineSensor(RobotMap.DRIVETRAIN_LINE_SENSOR_RIGHT_PORT);
        
        gearShift = new Solenoid(RobotMap.GEAR_SHIFT_CHANNEL);

        differentialDrive = new DifferentialDrive(leftBottomMotor, rightBottomMotor);
        
        // the navX is plugged into the kMXP port on the roboRIO
        navX = new AHRS(SPI.Port.kMXP);
        
        absoluteGyro = 0;
    }
    //@Override
    /*public void periodic(){
        updateSensors();
    }*/
    
    public void resetRamping() {
        setRamp(0);
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

    public double getRawEncoderDistance() {
        return Math.abs(Math.max(getLeftRawEncoderDistance(), getRightRawEncoderDistance()));
    }
    
    public double getEncoderDistance() {
        return Math.abs(Math.max(getLeftEncoderDistance(), getRightEncoderDistance()));
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

    public void setEncoders(double inches){
        leftBottomMotor.setSelectedSensorPosition((int) (getLeftRawEncoderDistance() + (inches / RobotMap.DRIVETRAIN_RAW_MULTIPLIER)), 0,0);
        rightBottomMotor.setSelectedSensorPosition((int) (getRightRawEncoderDistance() + (inches / RobotMap.DRIVETRAIN_RAW_MULTIPLIER)), 0,0);
    }

    public void tankDrive(double left, double right) {
        differentialDrive.tankDrive(left, right, false);
    }

    public void arcadeDrive(double speed, double rotation) {
        differentialDrive.arcadeDrive(speed, rotation);
    }

    public void curvatureDrive(double speed, double rotation, boolean turn) {
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
    
    public void gearShiftInput(boolean isShifted) {
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

    public boolean isOnLine(int mode) {//TODO:Decide if we want to have different auton speeds(modes). If so, then create enums instead.
        return leftIsOnLine(mode) || rightIsOnLine(mode);
    }

    public boolean rightIsOnLine(int mode) {
        return rightLineSensor.basicFind();
    }

    public boolean leftIsOnLine(int mode) {
        return leftLineSensor.basicFind();
    }
    
    public double getRawLeftLineSensor() {
        return leftLineSensor.getRawData();
    }
    
    public double getRawRightLineSensor() {
        return rightLineSensor.getRawData();
    }

    public void initDefaultCommand() {
        //setDefaultCommand(new DriveCommand());
        setDefaultCommand(new DrivetrainDriveSystemCommand());
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
        absoluteGyro += getGyroAngle();
        navX.reset();
    }
    
    public double getAbsoluteGyro() {
        return absoluteGyro;
    }

    public void enableCurrentLimit() {
//        leftBottomMotor.enableCurrentLimit(true);
//        rightBottomMotor.enableCurrentLimit(true);
    }

    public void disableCurrentLimit() {
//        leftBottomMotor.enableCurrentLimit(false);
//        rightBottomMotor.enableCurrentLimit(false);
    }

}