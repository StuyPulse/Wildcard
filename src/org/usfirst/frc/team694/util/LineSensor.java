package org.usfirst.frc.team694.util;
import edu.wpi.first.wpilibj.AnalogInput;
import java.util.ArrayList;

import org.usfirst.frc.team694.robot.RobotMap;

public class LineSensor {
    /******************************************************************************
     * Line Sensor Constants
     *****************************************************************************/
    public static final double DRIVETRAIN_LINE_SENSOR_INITIALIZE_TIME = 3;
    public static final double DRIVETRAIN_LINE_SENSOR_THRESHOLD = -1.0;
    
	private int framesExsisted = 0;// frames since beginning of instance, as first 5 are used to callibrate.
	private int ambientLight;//Initial reading of carpet
    private boolean isChangedBefore = false;//Used to remove noise.
    private int Max = 0;//Used to test highest deviation of color, used to determine threshold.
    private int rawValue;//raw Data from analogInput.
    private boolean setupDone = false;//is it done reading initial color?
    private AnalogInput mySensor;//Line Sensor.
	public LineSensor(int Port){
		mySensor = new AnalogInput(Port);//initiates VEX line sensor (or any analog Input)
		rawValue = mySensor.getValue();
	}
	public double getRawData(){
		rawValue = mySensor.getValue();
		return rawValue;
	}
	public void initialLoop(){
	    setupDone = (framesExsisted == DRIVETRAIN_LINE_SENSOR_INITIALIZE_TIME);//used the initializing time to get initial readings. If it is passt the time, it's done reading.
	    ambientLight = rawValue;//set rug color to reading.
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
	public boolean basicFind(){//TODO: consider adding different speeds.
		if ((Math.abs(rawValue - ambientLight)) > Max){//if difference from rug color > maximum deviation, set maximum deviation to current deviation.
			Max = (Math.abs(rawValue - ambientLight));
		}
		System.out.println(Max);
		boolean temp = isChangedBefore;//used to make sure that the previous deviation and current deviation are different, as it might just be noise from moving the bot.
		isChangedBefore = ((Math.abs(rawValue - ambientLight)) < DRIVETRAIN_LINE_SENSOR_THRESHOLD);
		return (!temp) && ((Math.abs(rawValue - ambientLight)) < DRIVETRAIN_LINE_SENSOR_THRESHOLD);
	}
}
