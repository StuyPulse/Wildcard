package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.util.IRSensor;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Quisitor extends Subsystem {

    private WPI_VictorSPX quisitorMotor;//Motor for intake/outtake
    private DoubleSolenoid quisitorGrabberSolenoid;//solenoid for grabbing
//    private DigitalInput quisitorLimitSwitch;//Not used but here anyways
    private IRSensor quisitorCubeSensor;

    // Temporary LEDs for if the arduino solution doesn't work
    private DigitalOutput temporaryLED;

    public Quisitor() {
        quisitorMotor = new WPI_VictorSPX(RobotMap.QUISITOR_MOTOR_PORT);
        quisitorMotor.setNeutralMode(NeutralMode.Brake);

        if (Robot.IS_MILDCARD) {
            quisitorGrabberSolenoid = new DoubleSolenoid(
                    RobotMap.QUISITOR_GRABBER_SOLENOID_CLOSE_PORT_MILDCARD, 
                    RobotMap.QUISITOR_GRABBER_SOLENOID_OPEN_PORT_MILDCARD
                    );
        } else {
            quisitorGrabberSolenoid = new DoubleSolenoid(
                    RobotMap.QUISITOR_GRABBER_SOLENOID_CLOSE_PORT, 
                    RobotMap.QUISITOR_GRABBER_SOLENOID_OPEN_PORT
                    );
        }

//        quisitorLimitSwitch = new DigitalInput(RobotMap.QUISITOR_LIMIT_SWITCH_PORT);
        quisitorCubeSensor = new IRSensor(RobotMap.QUISITOR_IR_SENSOR_PORT);

        temporaryLED = new DigitalOutput(RobotMap.DIO_PI_PORT);
        temporaryLED.set(false);
    }

    @Override
    public void initDefaultCommand() {
        //setDefaultCommand(new QuisitorMoveControlCommand());
    }

    @Override
    public void periodic() {
        super.periodic();
        temporaryLED.set(this.isCubeDetected());
    }

    public void acquire() {
        quisitorMotor.set(1);
    }

    public void deacquire() {
        quisitorMotor.set(-1);
    }

    public void acquireSpeed(double speed) {
        quisitorMotor.set(speed);
    }

    public void stop() {
        quisitorMotor.set(0);
    }

    public boolean isCubeDetected() {
       return quisitorCubeSensor.isSensorTriggered();
//        return !(quisitorLimitSwitch.get());
    }

    public void setLEDNeutral() {
        quisitorCubeSensor.setNeutral();
    }

    public void setLEDForward() {
        quisitorCubeSensor.setForward();
    }

    public void open() {
        quisitorGrabberSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void close() {
        quisitorGrabberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void toggle() {
        if (quisitorGrabberSolenoid.get() == DoubleSolenoid.Value.kForward) {
            close();
        } else {
            open();
        }
    }

}
