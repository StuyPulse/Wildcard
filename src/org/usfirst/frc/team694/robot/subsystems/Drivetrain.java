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

        leftTopMotor.setNeutralMode(NeutralMode.Brake);
        leftMiddleMotor.setNeutralMode(NeutralMode.Brake);
        leftBottomMotor.setNeutralMode(NeutralMode.Brake);
        rightTopMotor.setNeutralMode(NeutralMode.Brake);
        rightMiddleMotor.setNeutralMode(NeutralMode.Brake);
        rightBottomMotor.setNeutralMode(NeutralMode.Brake);

        leftBottomMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        rightBottomMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        leftLineSensor = new LineSensor(RobotMap.DRIVETRAIN_LINE_SENSOR_LEFT_PORT);
        rightLineSensor = new LineSensor(RobotMap.DRIVETRAIN_LINE_SENSOR_RIGHT_PORT);
        
        gearShift = new Solenoid(RobotMap.GEAR_SHIFT_CHANNEL);

        //leftEncoder.setDistancePerPulse(RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);
        //rightEncoder.setDistancePerPulse(RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);

        differentialDrive = new DifferentialDrive(leftBottomMotor, rightBottomMotor);
        
        // the navX is plugged into the kMXP port on the roboRIO
        navX = new AHRS(SPI.Port.kMXP);
    }
    @Override
    public void periodic(){
        updateSensors();
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
        return rightBottomMotor.getSelectedSensorPosition(0) * RobotMap.DRIVETRAIN_RAW_MULTIPLIER;
    }

    public double getLeftRawEncoderDistance() {
        return leftBottomMotor.getSelectedSensorPosition(0);
    }

    public double getRightRawEncoderDistance() {
        return rightBottomMotor.getSelectedSensorPosition(0);
    }

    public void resetEncoders() {
        leftBottomMotor.setSelectedSensorPosition(0, 0, 0);
        rightBottomMotor.setSelectedSensorPosition(0, 0, 0);
    }

    public void tankDrive(double left, double right) {
        differentialDrive.tankDrive(left, right);
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

    public void gearShiftInput(boolean isShifted) {
        gearShift.set(isShifted);
    }
    public void resetLineSensors(){
        leftLineSensor.resetAmbient();
        rightLineSensor.resetAmbient();
    }

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

    public void initDefaultCommand() {
        //setDefaultCommand(new DriveCommand());
        setDefaultCommand(new DrivetrainDriveSystemCommand());
    }

    public void resetGyro() {
        navX.reset();
    }
    
}