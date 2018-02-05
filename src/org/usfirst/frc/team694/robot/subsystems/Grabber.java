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