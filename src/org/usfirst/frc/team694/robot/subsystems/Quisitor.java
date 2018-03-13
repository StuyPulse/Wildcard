package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Quisitor extends Subsystem {

    private WPI_VictorSPX quisitorLeftMotor;
    private WPI_VictorSPX quisitorRightMotor;
    private DoubleSolenoid quisitorGrabberSolenoid;
    //private Solenoid quisitorTongsSolenoid; 

    private DigitalInput quisitorLimitSwitch;

    public boolean isBITCOINAutomation;

//    public boolean quisitorRunning;

    public Quisitor() {
        quisitorLeftMotor = new WPI_VictorSPX(RobotMap.QUISITOR_LEFT_MOTOR_PORT);
        quisitorRightMotor = new WPI_VictorSPX(RobotMap.QUISITOR_RIGHT_MOTOR_PORT);
        
        quisitorLeftMotor.setNeutralMode(NeutralMode.Brake);
        quisitorRightMotor.setNeutralMode(NeutralMode.Brake);
        
        quisitorGrabberSolenoid = new DoubleSolenoid(RobotMap.QUISITOR_GRABBER_SOLENOID_LEFT_PORT, RobotMap.QUISITOR_GRABBER_SOLENOID_RIGHT_PORT);

        quisitorLimitSwitch = new DigitalInput(RobotMap.QUISITOR_LIMIT_SWITCH_PORT);

        isBITCOINAutomation = false;
    }

    @Override
    public void initDefaultCommand() {
        //setDefaultCommand(new QuisitorStopCommand());
    }

    @Override
    public void periodic() {
        super.periodic();
        SmartDashboard.putString("Quisitor Current Command", this.getCurrentCommandName());
    }

    public void acquire() {
        quisitorLeftMotor.set(1);
        quisitorRightMotor.set(1);
    }

    public void deacquire() {
        quisitorLeftMotor.set(-1);
        quisitorRightMotor.set(-1);
    }
    
    public void leftQuisitorAcquire() {
        quisitorLeftMotor.set(1);
    }
    
    public void rightQuisitorAcquire() {
        quisitorRightMotor.set(1);
    }
    
    public void leftQuisitorDeacquire() {
        quisitorLeftMotor.set(-1);
    }
    
    public void rightQuisitorDeacquire() {
        quisitorRightMotor.set(-1);
    }
    // TESTING
    public void acquireSpeed(double speed) {
        quisitorLeftMotor.set(speed);
        quisitorRightMotor.set(speed);
    }

    public void stop() {
        quisitorLeftMotor.set(0);
        quisitorRightMotor.set(0);
    }

    public boolean isCubeDetected() {
        return !(quisitorLimitSwitch.get());
    }
    
    //This is moved from the old Grabber Subsystem.
    public void open() {
        quisitorGrabberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void close() {
        quisitorGrabberSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void toggle() {
        if (quisitorGrabberSolenoid.get() == DoubleSolenoid.Value.kReverse) {
            close();
        } else {
            open();
        }
    }
}
