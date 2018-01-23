/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.OI;
import org.usfirst.frc.team694.robot.commands.DrivetrainPiotrDriveCommand;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
    private static WPI_TalonSRX leftFrontMotor;
    private static WPI_TalonSRX leftRearMotor;
    private static WPI_TalonSRX rightFrontMotor;
    private static WPI_TalonSRX rightRearMotor;

    private SpeedControllerGroup leftSpeedController;
    private SpeedControllerGroup rightSpeedController;

    public static OI oi;

    private static DifferentialDrive differentialDrive;

    private static Encoder leftEncoder;
    private static Encoder rightEncoder;

    public Drivetrain() {
        //TODO: Remove magic numbers: Add in RobotMap
        leftFrontMotor = new WPI_TalonSRX(1);
        leftRearMotor = new WPI_TalonSRX(2);
        leftSpeedController = new SpeedControllerGroup(leftFrontMotor, leftRearMotor);

        rightFrontMotor = new WPI_TalonSRX(3);
        rightRearMotor = new WPI_TalonSRX(4);
        rightSpeedController = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);

        leftFrontMotor.setInverted(false);
        leftRearMotor.setInverted(false);
        rightFrontMotor.setInverted(false);
        rightRearMotor.setInverted(false);

        leftFrontMotor.setNeutralMode(NeutralMode.Brake);
        leftRearMotor.setNeutralMode(NeutralMode.Brake);
        rightFrontMotor.setNeutralMode(NeutralMode.Brake);
        rightRearMotor.setNeutralMode(NeutralMode.Brake);

        leftEncoder = new Encoder(0, 1);
        rightEncoder = new Encoder(2, 3);

        //leftEncoder.setDistancePerPulse(RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);
        //rightEncoder.setDistancePerPulse(RobotMap.DRIVETRAIN_ENCODER_INCHES_PER_PULSE);

        differentialDrive = new DifferentialDrive(leftSpeedController, rightSpeedController);

        oi = new OI();

    }

    public static void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public static double getLeftSpeed() {
        return leftEncoder.getRate();
    }

    public static double getRightSpeed() {
        return rightEncoder.getRate();
    }

    public static double getSpeed() {
        return Math.max(Math.abs(getLeftSpeed()), Math.abs(getRightSpeed()));
    }

    public static double getEncoderDistance() {
        return Math.abs(Math.max(getLeftEncoderDistance(), getRightEncoderDistance()));
    }

    public static double getLeftEncoderDistance() {
        return leftEncoder.getDistance();
    }

    public static double getRightEncoderDistance() {
        return rightEncoder.getDistance();
    }

    public static void tankDrive(double left, double right) {
        differentialDrive.tankDrive(left, right);
    }

    public static void arcadeDrive(double speed, double rotation) {
        differentialDrive.arcadeDrive(speed, rotation);
    }

    public static void stop() {
        tankDrive(0, 0);
    }

    /*TODO: Should we remove?
    public static int leftEncoderRaw() {
        return leftEncoder.getRaw();
    }
    
    public static int rightEncoderRaw() {
        return rightEncoder.getRaw();
    }
    
    public static int encoderRaw() {
        return Math.abs(Math.max(rightEncoderRaw(), leftEncoderRaw()));
    }*/

    public void initDefaultCommand() {
        //setDefaultCommand(new DriveCommand());
        setDefaultCommand(new DrivetrainPiotrDriveCommand());
    }

}
