package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {
    private Solenoid grabberSolenoid;
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
        grabberSolenoid.set(true);
    }

    public void close() {
        grabberSolenoid.set(false);
    }

    public void toggle() {
        if (grabberSolenoid.get()) {
            close();
        } else {
            open();
        }
    }
}
