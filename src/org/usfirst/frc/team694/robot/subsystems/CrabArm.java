package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.CrabArmStopCommand;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CrabArm extends Subsystem {
    private WPI_VictorSPX leftCrabArmMotor;
    private WPI_VictorSPX rightCrabArmMotor;
    private SpeedControllerGroup crabArmMotors;

    public CrabArm() {
        leftCrabArmMotor = new WPI_VictorSPX(RobotMap.CRAB_ARM_LEFT_MOTOR_PORT);
        rightCrabArmMotor = new WPI_VictorSPX(RobotMap.CRAB_ARM_RIGHT_MOTOR_PORT);
        leftCrabArmMotor.setNeutralMode(NeutralMode.Coast);
        rightCrabArmMotor.setNeutralMode(NeutralMode.Coast);
        
        leftCrabArmMotor.setInverted(true);
        
        crabArmMotors = new SpeedControllerGroup(leftCrabArmMotor, rightCrabArmMotor);
    }
    
    public void acquire() {
        crabArmMotors.set(1);
    }

    public void flapOut() {
        crabArmMotors.set(-1);
    }

    public void stop() {
        crabArmMotors.set(0);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new CrabArmStopCommand());
    }
}