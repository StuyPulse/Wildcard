package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CrabArm extends Subsystem {
    private Solenoid deploySolenoid;
    private Solenoid flapSolenoid;
    private WPI_VictorSPX leftCrabArmMotor;
    private WPI_VictorSPX rightCrabArmMotor;
    private SpeedControllerGroup crabArmMotors;

    public CrabArm() {
        deploySolenoid = new Solenoid(RobotMap.CRAB_ARM_SOLENOID_PORT);
        leftCrabArmMotor = new WPI_VictorSPX(RobotMap.CRAB_ARM_LEFT_MOTOR_PORT);
        rightCrabArmMotor = new WPI_VictorSPX(RobotMap.CRAB_ARM_RIGHT_MOTOR_PORT);
        leftCrabArmMotor.setNeutralMode(NeutralMode.Coast);
        rightCrabArmMotor.setNeutralMode(NeutralMode.Coast);
        leftCrabArmMotor.setInverted(true);
        crabArmMotors = new SpeedControllerGroup(leftCrabArmMotor, rightCrabArmMotor);
    }

    public void deploy() {
        deploySolenoid.set(true);
    }
    
    public void acquire() {
        crabArmMotors.set(1);
    }

    public void deacquire() {
        crabArmMotors.set(-1);
    }

    public void flapOut() {
        flapSolenoid.set(true);
    }
    
    public void flapIn() {
        flapSolenoid.set(false);
    }
    public void initDefaultCommand() {
    }
}
