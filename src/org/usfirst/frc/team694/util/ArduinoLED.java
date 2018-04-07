package org.usfirst.frc.team694.util;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.I2C;

/**   ArduinoLED
 *      This controls the arduino controller that controls the LED's on our bot
 *      The "state" of our LED's is in the LED_STATE enum, and is handled in a
 *      state machine.
 */

public class ArduinoLED {

    // For state machine
    public static enum LED_STATE {
        OFF,
        STROBE_RED_BLUE,
        SOLID_BLUE,
        SOLID_GREEN,
        SOLID_YELLOW,
        SOLID_PINK,
        SOLID_WHITE,
        RAINBOW
    };

    private LED_STATE state;

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

        state = LED_STATE.OFF;
    }

    private void sendData(byte mode) {
        arduinoPort.write(deviceAddress, mode);
    }

    // CUSTOMIZE ME
    private void setLightState() {

        boolean cubeDetected = Robot.quisitor.isCubeDetected();
        boolean operatorSignalPress = Robot.oi.operatorGamepad.getRawDPadUp();

        state = LED_STATE.OFF;
        if (cubeDetected) {
            state = LED_STATE.SOLID_GREEN;
        }
        if (operatorSignalPress) {
            state = LED_STATE.RAINBOW;
        }
    }

    // CALL ME
    public void periodic() {
        setLightState();

        // Send proper data to the light based on our state
        switch (state) {
            case OFF:
                sendData(ALL_OFF);
                break;
            case STROBE_RED_BLUE:
                sendData(RED_BLUE_STROBE);
                break;
            case SOLID_BLUE:
                sendData(ALLIANCE_BLUE);
                break;
            case SOLID_GREEN:
                sendData(SOLID_GREEN);
                break;
            case SOLID_YELLOW:
                sendData(SOLID_YELLOW);
                break;
            case SOLID_PINK:
                sendData(SOLID_PINK);
                break;
            case SOLID_WHITE:
                sendData(SOLID_WHITE);
                break;
            case RAINBOW:
                sendData(RAINBOW);
                break;
        }
    }
}
