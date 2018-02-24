package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

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

//    public boolean spatulaRunning;

    public Spatula() {
        leftSpatulaMotor = new WPI_VictorSPX(RobotMap.SPATULA_LEFT_MOTOR_PORT);
        rightSpatulaMotor = new WPI_VictorSPX(RobotMap.SPATULA_RIGHT_MOTOR_PORT);
        leftSpatulaMotor.setNeutralMode(NeutralMode.Brake);
        rightSpatulaMotor.setNeutralMode(NeutralMode.Brake);

        rightSpatulaMotor.setInverted(true);

        spatulaFlipSolenoid = new DoubleSolenoid(RobotMap.SPATULA_FLIP_UP_PORT, RobotMap.SPATULA_FLIP_DOWN_PORT);
        spatulaMotors = new SpeedControllerGroup(leftSpatulaMotor, rightSpatulaMotor);

        spatulaLimitSwitch = new DigitalInput(RobotMap.SPATULA_LIMIT_SWITCH_PORT);

        isBITCOINAutomation = true;
    }

    @Override
    public void initDefaultCommand() {
        //setDefaultCommand(new SpatulaStopCommand());
    }

    public void acquire() {
        spatulaMotors.set(1);
    }

    public void deacquire() {
        spatulaMotors.set(-1);
    }
    
    // TESTING
    public void acquireSpeed(double speed) {
        spatulaMotors.set(speed);
    }

    //left spatula deacquires and the right acquires, rotating cube clockwise
    public void leftSpatulaDeacquire() {
        leftSpatulaMotor.set(1);
        rightSpatulaMotor.set(-1);
    }

    //right spatula deacquires and the left acquires, rotating cube counterclockwise
    public void rightSpatulaDeacquire() {
        leftSpatulaMotor.set(-1);
        rightSpatulaMotor.set(1);
    }

    public void stop() {
        spatulaMotors.set(0);
    }

    public void flipUp() {
        spatulaFlipSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void flipDown() {
        spatulaFlipSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void flipToggle() {
        System.out.println("[Spatula] Spatula up? " + isSpatulaUp() + ", Solenoid Value: " + spatulaFlipSolenoid.get());
        if (isSpatulaUp()) {
            flipDown();
        } else {
            flipUp();
        }
    }

    public boolean isSpatulaUp() {
        DoubleSolenoid.Value val = spatulaFlipSolenoid.get();
        return val == DoubleSolenoid.Value.kForward || val == DoubleSolenoid.Value.kOff;
    }

    public boolean isCubeDetected() {
        return !(spatulaLimitSwitch.get());
    }
}
