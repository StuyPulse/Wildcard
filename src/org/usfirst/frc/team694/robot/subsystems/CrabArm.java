package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.CrabArmStopCommand;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CrabArm extends Subsystem {
    private Solenoid crabArmDeploySolenoid;
    private Solenoid crabArmFlapperSolenoid;
    private WPI_VictorSPX leftCrabArmMotor;
    private WPI_VictorSPX rightCrabArmMotor;
    private SpeedControllerGroup crabArmMotors;
    private boolean isOpen;

    public CrabArm() {
        crabArmDeploySolenoid = new Solenoid(RobotMap.CRAB_ARM_DEPLOY_SOLENOID_PORT);
        crabArmFlapperSolenoid = new Solenoid(RobotMap.CRAB_ARM_FLAPPER_SOLENOID_PORT);
        leftCrabArmMotor = new WPI_VictorSPX(RobotMap.CRAB_ARM_LEFT_MOTOR_PORT);
        rightCrabArmMotor = new WPI_VictorSPX(RobotMap.CRAB_ARM_RIGHT_MOTOR_PORT);
        leftCrabArmMotor.setNeutralMode(NeutralMode.Coast);
        rightCrabArmMotor.setNeutralMode(NeutralMode.Coast);
        leftCrabArmMotor.setInverted(true);
        crabArmMotors = new SpeedControllerGroup(leftCrabArmMotor, rightCrabArmMotor);
    }

    public void deploy() {
        crabArmDeploySolenoid.set(true);
    }
    
    public void acquire() {
        crabArmMotors.set(1);
    }

    public void deacquire() {
        crabArmMotors.set(-1);
    }

    public void stop() {
        crabArmMotors.set(0);
    }

    public void flapOut() {
        isOpen = true;
        crabArmFlapperSolenoid.set(isOpen);
        
    }

    public void flapIn() {
        isOpen = false;
        crabArmFlapperSolenoid.set(isOpen);
    }

    public void flapToggle() {
        if (isOpen) {
            flapIn();
        } else {
            flapOut();
        }
    }

    public void initDefaultCommand() {
        setDefaultCommand(new CrabArmStopCommand());
    }
}
