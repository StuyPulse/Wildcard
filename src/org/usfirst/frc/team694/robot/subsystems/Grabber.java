package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grabber extends Subsystem {
    public Solenoid grabberSolenoid;
    public boolean isOpen;

    public Grabber() {
        grabberSolenoid = new Solenoid(RobotMap.GRABBER_SOLENOID_PORT);
    }

    public void initDefaultCommand() {
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
        } else {
            open();
        }
    }
}