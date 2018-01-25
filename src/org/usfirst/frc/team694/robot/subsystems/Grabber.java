package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {
    public Solenoid grabberSolenoid;
    public boolean isOpen;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Grabber() {
        grabberSolenoid = new Solenoid(RobotMap.GRABBER_SOLENOID_PORT);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void open() {
        isOpen = true; 
        grabberSolenoid.set(isOpen);
    }
    public void close() {
        isOpen = false; 
        grabberSolenoid.set(isOpen);
    }
    public void toggle() {
       if (isOpen) {
           close();
       }else {
           open();
       }
    }
}
