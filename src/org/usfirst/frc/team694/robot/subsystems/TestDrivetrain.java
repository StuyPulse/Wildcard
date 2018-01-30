package org.usfirst.frc.team694.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TestDrivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private PWMTalonSRX topMotor;
    private PWMTalonSRX midMotor;
    private PWMTalonSRX bottomMotor;
    private SpeedControllerGroup leftMotors;
    
    public TestDrivetrain() {
        topMotor = new PWMTalonSRX(0);
        midMotor = new PWMTalonSRX(1);
        bottomMotor = new PWMTalonSRX(2);
        leftMotors = new SpeedControllerGroup(topMotor, midMotor, bottomMotor);
    }
    
    public void arcadeDrive (double a) {
        leftMotors.set(a);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

