package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Grabber extends Subsystem {

    private DoubleSolenoid grabberSolenoid;

    public Grabber() {
        grabberSolenoid = new DoubleSolenoid(RobotMap.GRABBER_SOLENOID_OPEN_PORT, RobotMap.GRABBER_SOLENOID_CLOSE_PORT);
    }

    public void initDefaultCommand() {
    }

    public void open() {
        grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void close() {
        grabberSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void toggle() {
        if (grabberSolenoid.get() == DoubleSolenoid.Value.kReverse) {
            close();
        } else {
            open();
        }
    }
}