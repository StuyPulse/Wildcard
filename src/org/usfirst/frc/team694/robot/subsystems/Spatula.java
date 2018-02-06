package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Spatula extends Subsystem {

    private WPI_VictorSPX leftSpatulaMotor;
    private WPI_VictorSPX rightSpatulaMotor;
    private Solenoid spatulaFlipSolenoid;
    private Solenoid spatulaTongsSolenoid; 

    private SpeedControllerGroup spatulaMotors;
    private DigitalInput limitSwitch;
    
    private SpeedControllerGroup acquirerMotors;

    public boolean isBITCOINAutomation;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
}

    public Spatula() {
        leftSpatulaMotor = new WPI_VictorSPX(RobotMap.SPATULA_LEFT_MOTOR_PORT);
        rightSpatulaMotor = new WPI_VictorSPX(RobotMap.SPATULA_RIGHT_MOTOR_PORT);
        leftSpatulaMotor.setNeutralMode(NeutralMode.Coast);
        rightSpatulaMotor.setNeutralMode(NeutralMode.Coast);
        rightSpatulaMotor.setInverted(true);
        
        spatulaFlipSolenoid = new Solenoid(RobotMap.SPATULA_FLIP_SOLENOID_PORT);
        spatulaTongsSolenoid = new Solenoid(RobotMap.SPATULA_TONGS_SOLENOID_PORT);
        spatulaMotors = new SpeedControllerGroup(leftSpatulaMotor, rightSpatulaMotor);

        acquirerMotors = new SpeedControllerGroup(leftSpatulaMotor, rightSpatulaMotor);

        isBITCOINAutomation = true;
        limitSwitch = new DigitalInput(RobotMap.ACQUIRER_LIMIT_SWITCH_PORT);
    }

    public void acquire() {
        spatulaMotors.set(1);
    }

    public void deacquire() {
        spatulaMotors.set(-1);
    }
    
    public void stop() {
        acquirerMotors.set(0);
    }

    public void flipUp() {
        spatulaFlipSolenoid.set(true);
    }

    public void flipDown() {
        spatulaFlipSolenoid.set(false);
    }
    
    public void tightenCubeGrip() {
        spatulaTongsSolenoid.set(true);
    }
    
    public void loosenCubeGrip() {
        spatulaTongsSolenoid.set(false);
    }
    
    public boolean getCurrentStateOfLimitSwitch() {
        return limitSwitch.get();
    }
}