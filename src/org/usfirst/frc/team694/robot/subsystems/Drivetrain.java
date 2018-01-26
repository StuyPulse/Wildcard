/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.            */
/* Open Source Software - may be modified and shared by FRC teams. The code  */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import org.usfirst.frc.team694.robot.OI;
import org.usfirst.frc.team694.robot.commands.DrivetrainPiotrDriveCommand;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
    private WPI_TalonSRX leftFrontMotor;
    private WPI_TalonSRX leftMiddleMotor;
    private WPI_TalonSRX leftRearMotor;
    private WPI_TalonSRX rightFrontMotor;
    private WPI_TalonSRX rightMiddleMotor;
    private WPI_TalonSRX rightRearMotor;

    private SpeedControllerGroup leftDrivetrainMotorGroup;
    private SpeedControllerGroup rightDrivetrainMotorGroup;

    private DifferentialDrive differentialDrive;

    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private Solenoid gearShift;

    public Drivetrain() {
        //TODO: Remove magic numbers: Add in RobotMap
        leftFrontMotor = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR_PORT);
        leftMiddleMotor = new WPI_TalonSRX(RobotMap.LEFT_MIDDLE_MOTOR_PORT);
        leftRearMotor = new WPI_TalonSRX(RobotMap.LEFT_REAR_MOTOR_PORT);
        leftDrivetrainMotorGroup = new SpeedControllerGroup(leftFrontMotor, leftMiddleMotor, leftRearMotor);

        rightFrontMotor = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR_PORT);
        rightMiddleMotor = new WPI_TalonSRX(RobotMap.RIGHT_MIDDLE_MOTOR_PORT);
        rightRearMotor = new WPI_TalonSRX(RobotMap.RIGHT_REAR_MOTOR_PORT);
        rightDrivetrainMotorGroup = new SpeedControllerGroup(rightFrontMotor, rightMiddleMotor, rightRearMotor);

        leftDrivetrainMotorGroup.setInverted(true);

        leftFrontMotor.setNeutralMode(NeutralMode.Coast);
        leftMiddleMotor.setNeutralMode(NeutralMode.Coast);
        leftRearMotor.setNeutralMode(NeutralMode.Coast);
        rightFrontMotor.setNeutralMode(NeutralMode.Coast);
        rightMiddleMotor.setNeutralMode(NeutralMode.Coast);
        rightRearMotor.setNeutralMode(NeutralMode.Coast);

        leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_CHANNEL_A, RobotMap.LEFT_ENCODER_CHANNEL_B);
        rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_CHANNEL_A, RobotMap.RIGHT_ENCODER_CHANNEL_B);

        gearShift = new Solenoid(RobotMap.GEAR_SHIFT_CHANNEL);
        //leftEncoder.setDistancePerPulse(RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);
        //rightEncoder.setDistancePerPulse(RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);

        differentialDrive = new DifferentialDrive(leftDrivetrainMotorGroup, rightDrivetrainMotorGroup);

    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public double getLeftSpeed() {
        return leftEncoder.getRate();
    }

    public double getRightSpeed() {
        return rightEncoder.getRate();
    }

    public double getSpeed() {
        return Math.max(Math.abs(getLeftSpeed()), Math.abs(getRightSpeed()));
    }

    public double getEncoderDistance() {
        return Math.abs(Math.max(getLeftEncoderDistance(), getRightEncoderDistance()));
    }

    public double getLeftEncoderDistance() {
        return leftEncoder.getDistance();
    }

    public double getRightEncoderDistance() {
        return rightEncoder.getDistance();
    }

    public void tankDrive(double left, double right) {
        differentialDrive.tankDrive(left, right);
    }

    public void arcadeDrive(double speed, double rotation) {
        differentialDrive.arcadeDrive(speed, rotation);
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

    /*TODO: Should we remove?
    public int leftEncoderRaw() {
    return leftEncoder.getRaw();
    }
    
    public int rightEncoderRaw() {
    return rightEncoder.getRaw();
    }
    
    public int encoderRaw() {
    return Math.abs(Math.max(rightEncoderRaw(), leftEncoderRaw()));
    }*/

    public void initDefaultCommand() {
        //setDefaultCommand(new DriveCommand());
        setDefaultCommand(new DrivetrainPiotrDriveCommand());
    }

}
