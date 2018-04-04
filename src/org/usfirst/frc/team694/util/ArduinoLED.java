package org.usfirst.frc.team694.util;

import edu.wpi.first.wpilibj.I2C;

public class ArduinoLED {

    private int deviceAddress;
    private I2C arduinoPort;
    
    public static final byte ALLIANCE_BLUE = (byte) '?';
    public static final byte ALL_OFF = (byte) 'A';
    public static final byte PULSE_ALLIANCE = (byte) 'C';
    public static final byte PULSE_GREEN = (byte) 'E';
    public static final byte SOLID_GREEN = (byte) 'G';
    public static final byte TRIPLE_FLASH = (byte) 'I';
    public static final byte RAINBOW = (byte) 'K';
    public static final byte PURPLE_STROBE = (byte) ' '; //TODO: Replace with a real value.
    
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

    public void sendPulseGreen() {
        sendData(PULSE_GREEN);
    }

    public void sendTripleFlash() {
        sendData(TRIPLE_FLASH);
    }
    
    public void sendPurpleStrobe() {
        sendData(PURPLE_STROBE);
    }
    public void sendGreen() {
        sendData(SOLID_GREEN);
    }
}
