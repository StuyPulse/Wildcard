package org.usfirst.frc.team694.util;

import edu.wpi.first.wpilibj.I2C;

public class ArduinoLED {

    private int deviceAddress;
    private I2C arduinoPort;
    
    public static final byte ALLIANCE_BLUE = (byte) '?';
    public static final byte ALL_OFF = (byte) 'a';
    public static final byte PULSE_ALLIANCE = (byte) 'c';
    public static final byte SOLID_GREEN = (byte) 'f';
    public static final byte RAINBOW = (byte) 'd';
    public static final byte RED_BLUE_STROBE = (byte) 'e';
    public static final byte SOLID_YELLOW = (byte) 'g';
    public static final byte SOLID_PINK = (byte) 'h';
    public static final byte SOLID_WHITE = (byte) 'j';
    
    
    public ArduinoLED(int deviceAddress) {
        this.deviceAddress = deviceAddress;
        arduinoPort = new I2C(I2C.Port.kOnboard, deviceAddress);
    }
    
    public void sendData(byte mode) {
        arduinoPort.write(deviceAddress, mode);
    }

    public void setAllianceRed() {
    }
    
    public void setAllianceBlue() {
        sendData(ALLIANCE_BLUE);
    }

    public void sendPulseAlliance() {
        sendData(PULSE_ALLIANCE);
    }

    public void sendAllOff() {
        sendData(ALL_OFF);
    }

    public void sendRainbow() {
        sendData(RAINBOW);
    }

    public void sendRedBlueStrobe() {
        sendData(RED_BLUE_STROBE);
    }
    
    public void sendGreen() {
        sendData(SOLID_GREEN);
    }
    
    public void sendYellow() {
        sendData(SOLID_YELLOW);
    }
    
    public void sendPink() {
        sendData(SOLID_PINK);
    }
    
    public void sendWhite() {
        sendData(SOLID_WHITE);
    }
}
