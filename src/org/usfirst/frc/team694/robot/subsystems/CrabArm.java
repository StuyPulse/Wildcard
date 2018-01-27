package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CrabArm extends Subsystem {
    public Solenoid crabArmSolenoidInitial;
    public Solenoid crabArmSolenoidPush;
    public WPI_VictorSPX leftCrabArmMotor;
    public WPI_VictorSPX rightCrabArmMotor;
    public SpeedControllerGroup crabArmMotors;

    public CrabArm() {
        crabArmSolenoidInitial = new Solenoid(RobotMap.CRAB_ARM_SOLENOID_INITIAL_PORT);
        crabArmSolenoidPush = new Solenoid(RobotMap.CRAB_ARM_SOLENOID_PUSH_PORT);
        leftCrabArmMotor = new WPI_VictorSPX(RobotMap.CRAB_ARM_LEFT_MOTOR_PORT);
        rightCrabArmMotor = new WPI_VictorSPX(RobotMap.CRAB_ARM_RIGHT_MOTOR_PORT);
        leftCrabArmMotor.setInverted(true);
        crabArmMotors = new SpeedControllerGroup(leftCrabArmMotor, rightCrabArmMotor);
    }

    public void deployInitial() {
        crabArmSolenoidInitial.set(true);
    }
    
    public void deployPush() {
        crabArmSolenoidPush.set(true);
        Timer.delay(0.5);
    }
    
    public void retractPush() {
        crabArmSolenoidPush.set(false);
    }

    public void acquire() {
        crabArmMotors.set(1);
    }

    public void deacquire() {
        crabArmMotors.set(-1);
    }

    public void initDefaultCommand() {
    }
}
