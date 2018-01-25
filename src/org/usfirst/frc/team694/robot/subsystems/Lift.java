package org.usfirst.frc.team694.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class Lift extends Subsystem {
    
    //Motors
    private WPI_TalonSRX leftLiftMotor;
    private WPI_TalonSRX rightLiftMotor;
    //Solenoid brake system
    private Solenoid LiftBrake;
    //Limit switch
    private DigitalInput limitSwitch;
    //Encoder
    private Encoder liftEncoder;
    //Overridden boolean
   // private boolean overridden;
    
    public Lift() {
        
        //Assigning motors to ports in RobotMap
        //leftLiftMotor = new WPI_TalonSRX(RobotMap.FIXME);
        //rightLiftMotor= new WPI_TalonSRX(RobotMap.FIXME);

        leftLiftMotor.setNeutralMode(NeutralMode.Brake);
        rightLiftMotor.setNeutralMode(NeutralMode.Brake);
          
        leftLiftMotor.setInverted(true);
        rightLiftMotor.setInverted(true);

    }

    public void initDefaultCommand() {
    }

    public void resetEncoders() {
        liftEncoder.reset();
    }

   // private void setBrake(boolean on) {
     //   leftLiftMotor.
       // rightLiftMotor.
    //}

    public void goUp() {
        rightLiftMotor.set(1.0);
        leftLiftMotor.set(1.0);
    }
    
    public void goDown() {
        rightLiftMotor.set(-1.0);
        leftLiftMotor.set(-1.0);
    }
    
    public void stop() {
        rightLiftMotor.set(0);
        leftLiftMotor.set(0);
    }

    public boolean isAtBottom() {
        return limitSwitch.get();
    }

    public boolean isAtTop() { 
       return getLiftEncoderDistance() >= 999; //&& !overridden; //FIXME: You need a RobotMap value to replace the 999
    }
    
    public void checkForReset() {
        if (isAtBottom()) {
            liftEncoder.reset();
        }
    }
    
    public double getLiftEncoderDistance() {
        return liftEncoder.getDistance();
    }
}