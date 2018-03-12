package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Quisitor extends Subsystem {

    private WPI_VictorSPX leftQuisitorMotor;
    private WPI_VictorSPX rightQuisitorMotor;
    private DoubleSolenoid quisitorFlipSolenoid;
    private DoubleSolenoid quisitorGrabberSolenoid;
    //private Solenoid quisitorTongsSolenoid; 

    private SpeedControllerGroup quisitorMotors;
    private DigitalInput quisitorLimitSwitch;

    public boolean isBITCOINAutomation;

//    public boolean quisitorRunning;

    public Quisitor() {
        leftQuisitorMotor = new WPI_VictorSPX(RobotMap.QUISITOR_LEFT_MOTOR_PORT);
        rightQuisitorMotor = new WPI_VictorSPX(RobotMap.QUISITOR_RIGHT_MOTOR_PORT);
        leftQuisitorMotor.setNeutralMode(NeutralMode.Brake);
        rightQuisitorMotor.setNeutralMode(NeutralMode.Brake);

        rightQuisitorMotor.setInverted(true);

        quisitorFlipSolenoid = new DoubleSolenoid(RobotMap.QUISITOR_FLIP_UP_PORT, RobotMap.QUISITOR_FLIP_DOWN_PORT);
        quisitorGrabberSolenoid = new DoubleSolenoid(RobotMap.QUISITOR_GRABBER_SOLENOID_LEFT_PORT, RobotMap.QUISITOR_GRABBER_SOLENOID_RIGHT_PORT);
     //TODO: replace error with variables from Grabber   
        quisitorMotors = new SpeedControllerGroup(leftQuisitorMotor, rightQuisitorMotor);

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
        quisitorMotors.set(1);
    }

    public void deacquire() {
        quisitorMotors.set(-1);
    }
    
    // TESTING
    public void acquireSpeed(double speed) {
        quisitorMotors.set(speed);
    }

    //left quisitor deacquires and the right acquires, rotating cube clockwise
    public void leftQuisitorDeacquire() {
        leftQuisitorMotor.set(1);
        rightQuisitorMotor.set(-1);
    }

    //right quisitor deacquires and the left acquires, rotating cube counterclockwise
    public void rightQuisitorDeacquire() {
        leftQuisitorMotor.set(-1);
        rightQuisitorMotor.set(1);
    }

    public void stop() {
        quisitorMotors.set(0);
    }

    public void flipUp() {
        quisitorFlipSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void flipDown() {
        quisitorFlipSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void flipToggle() {
        System.out.println("[Quisitor] Quisitor up? " + isQuisitorUp() + ", Solenoid Value: " + quisitorFlipSolenoid.get());
        if (isQuisitorUp()) {
            flipDown();
        } else {
            flipUp();
        }
    }

    public boolean isQuisitorUp() {
        DoubleSolenoid.Value val = quisitorFlipSolenoid.get();
        return val == DoubleSolenoid.Value.kForward || val == DoubleSolenoid.Value.kOff;
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
