package org.usfirst.frc.team694.util;
import edu.wpi.first.wpilibj.AnalogInput;
import java.util.ArrayList;

import org.usfirst.frc.team694.robot.RobotMap;

public class LineSensor {
	private int framesExsisted = 0;
	private int ambientLight;
    private boolean isChangedBefore = false;
    private int Max = 0;
    private int rawValue;
    private boolean setupDone = false;
    private AnalogInput mySensor;
	public LineSensor(int Port){
		mySensor = new AnalogInput(Port);
		rawValue = mySensor.getValue();
	}
	public void getRawData(){
		rawValue = mySensor.getValue();
	}
	public void initialLoop(){
	    setupDone = (framesExsisted == RobotMap.DRIVETRAIN_LINE_SENSOR_INITIALIZE_TIME);
	    ambientLight = rawValue;
	}
	public void mainLoop(){
	  framesExsisted++;
	  getRawData();
	  if (!setupDone){
		  initialLoop();
	  }

	}
	public void resetAmbient(){
	    framesExsisted = 0;
	    setupDone = false;
	}
	public boolean basicFind(int mode){//modes 0-2 (0 is 0.25 ,1 is 0.5,2 is 0.75)
		if ((Math.abs(rawValue - ambientLight)) > Max){
			Max = (Math.abs(rawValue - ambientLight));
		}
		System.out.println(Max);
		boolean temp = isChangedBefore;
		isChangedBefore = ((Math.abs(rawValue - ambientLight)) < (Math.pow(mode, 2) * 15));
		return (!temp) && ((Math.abs(rawValue - ambientLight)) < (Math.pow(mode, 2) * 15));
	}
}
