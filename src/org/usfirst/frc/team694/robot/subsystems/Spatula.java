package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.util.IRSensor;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Spatula extends Subsystem {

    private WPI_VictorSPX leftAcquirerMotor;
    private WPI_VictorSPX rightAcquirerMotor;
    private Solenoid acquirerFlipSolenoid;
    private Solenoid acquirerSqueezeSolenoid; 

    private SpeedControllerGroup acquirerMotors;
    private DigitalInput acquirerLimitSwitch;
    public boolean isBITCOINAutomation;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
}

    public Spatula() {
        leftAcquirerMotor = new WPI_VictorSPX(RobotMap.ACQUIRER_LEFT_MOTOR_PORT);
        rightAcquirerMotor = new WPI_VictorSPX(RobotMap.ACQUIRER_RIGHT_MOTOR_PORT);
        leftAcquirerMotor.setNeutralMode(NeutralMode.Coast);
        rightAcquirerMotor.setNeutralMode(NeutralMode.Coast);
        acquirerFlipSolenoid = new Solenoid(RobotMap.ACQUIRER_FLIP_SOLENOID_PORT);
        acquirerSqueezeSolenoid = new Solenoid(RobotMap.ACQUIRER_SQUEEZE_SOLENOID_PORT);

        acquirerMotors = new SpeedControllerGroup(leftAcquirerMotor, rightAcquirerMotor);
        acquirerLimitSwitch = new DigitalInput(RobotMap.ACQUIRER_LIMIT_SWITCH_PORT);
        isBITCOINAutomation = true;
    }

    public void acquire() {
        acquirerMotors.set(1);
    }

    public void deacquire() {
        acquirerMotors.set(-1);
    }
    
    public void stop() {
        acquirerMotors.set(0);
    }

    public void flipUp() {
        acquirerFlipSolenoid.set(true);
    }

    public void flipDown() {
        acquirerFlipSolenoid.set(false);
    }
    
    public void tightenCubeGrip() {
        acquirerSqueezeSolenoid.set(true);
    }
    
    public void loosenCubeGrip() {
        acquirerSqueezeSolenoid.set(false);
    }
    
    public boolean getIsCubeDetected() {
          return acquirerLimitSwitch.get();
    }
}