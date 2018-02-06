package org.usfirst.frc.team694.robot.subsystems;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Grabber extends Subsystem {

    private DoubleSolenoid tongsSolenoid;

    public Grabber() {
        tongsSolenoid = new DoubleSolenoid(RobotMap.GRABBER_SOLENOID_OPEN, RobotMap.GRABBER_SOLENOID_CLOSE);
    }

    public void initDefaultCommand() {
    }

    public void open() {
        tongsSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void close() {
        tongsSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void toggle() {
        if (tongsSolenoid.get() == DoubleSolenoid.Value.kForward) {
            close();
        } else {
            open();
        }
    }
}