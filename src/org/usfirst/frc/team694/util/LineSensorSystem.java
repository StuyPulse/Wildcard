package org.usfirst.frc.team694.util;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

import java.util.ArrayList;

public class LineSensorSystem {
    private boolean onLineBefore;
    private boolean rightRawValue;
    private boolean leftRawValue;
    private boolean newLine;
    private String colorFound;
    private DigitalInput rightColorSensor;
    private DigitalInput leftColorSensor;
    public LineSensorSystem(DigitalInput right, DigitalInput left){
        rightColorSensor = right;
        leftColorSensor = left;
        colorFound = "grey";

    }
    public void getRawData(){
        rightRawValue = rightColorSensor.get();
        leftRawValue = leftColorSensor.get();
    }
    public boolean getRightSensorValue(){
        return rightRawValue;
    }
    public boolean getLeftSensorValue(){
        return leftRawValue;
    }
    public void mainLoop(){
      getRawData();
      if (!(rightRawValue || leftRawValue)){
          colorFound = "black/grey";
      }else{
          colorFound = "Red/White/Blue";

      }
      newLine = (rightRawValue  || leftRawValue) != onLineBefore;
      onLineBefore = newLine ;

    }
    public boolean basicFind(){
        if (newLine){
            System.out.println("port 2 DIO (DARK):" + rightRawValue);
            System.out.println("port 3 DIO (LIGHT):" + leftRawValue);
            System.out.println("new Color:" + colorFound);
        }
        return newLine;
    }
}
