package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.robot.commands.SpatulaStopCommand;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Spatula extends Subsystem {

    private WPI_VictorSPX leftSpatulaMotor;
    private WPI_VictorSPX rightSpatulaMotor;
    private DoubleSolenoid spatulaFlipSolenoid;
    //private Solenoid spatulaTongsSolenoid; 

    private SpeedControllerGroup spatulaMotors;
    private DigitalInput spatulaLimitSwitch;

    public boolean isBITCOINAutomation;

    public boolean spatulaRunning;

    public boolean isSpatulaRunning() {
        return spatulaRunning;
    }

    public void setSpatulaRunning(boolean status) {
        this.spatulaRunning = status;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new SpatulaStopCommand());
    }

    public Spatula() {
        leftSpatulaMotor = new WPI_VictorSPX(RobotMap.SPATULA_LEFT_MOTOR_PORT);
        rightSpatulaMotor = new WPI_VictorSPX(RobotMap.SPATULA_RIGHT_MOTOR_PORT);
        leftSpatulaMotor.setNeutralMode(NeutralMode.Brake);
        rightSpatulaMotor.setNeutralMode(NeutralMode.Brake);

        rightSpatulaMotor.setInverted(true);

        spatulaFlipSolenoid = new DoubleSolenoid(RobotMap.SPATULA_FLIP_UP_PORT, RobotMap.SPATULA_FLIP_DOWN_PORT);
        ;
        //spatulaTongsSolenoid = new Solenoid(RobotMap.SPATULA_TONGS_SOLENOID_PORT);
        spatulaMotors = new SpeedControllerGroup(leftSpatulaMotor, rightSpatulaMotor);
        isBITCOINAutomation = true;

        spatulaLimitSwitch = new DigitalInput(RobotMap.SPATULA_LIMIT_SWITCH_PORT);
    }

    public void acquire() {
        spatulaMotors.set(1);
    }

    public void deacquire() {
        spatulaMotors.set(-1);
    }

    //left spatula deacquires and the right acquires. This comment is used to avoid long method names
    public void leftSpatulaDeacquire() {
        leftSpatulaMotor.set(-1);
        rightSpatulaMotor.set(1);
    }

    //right spatula deacquires and the left acquires. This comment is used to avoid long method names
    public void rightSpatulaDeacquire() {
        leftSpatulaMotor.set(1);
        rightSpatulaMotor.set(-1);
    }

    public void stop() {
        spatulaMotors.set(0);
    }

    public void flipUp() {
        spatulaFlipSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void flipDown() {
        spatulaFlipSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public boolean isCubeDetected() {
        return !(spatulaLimitSwitch.get());
    }

    public void toggle() {
        if (spatulaFlipSolenoid.get() == DoubleSolenoid.Value.kForward) {
            flipUp();
        } else {
            flipDown();
        }
    }

    public boolean isSpatulaUp() {
        return spatulaFlipSolenoid.get() == DoubleSolenoid.Value.kReverse;
    }
}