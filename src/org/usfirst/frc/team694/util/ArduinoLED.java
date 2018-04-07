package org.usfirst.frc.team694.util;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
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
        PULSE_ALLIANCE,
        PULSE_POLICE,
        SOLID_ALLIANCE,
        SOLID_GREEN,
        SOLID_YELLOW,
        SOLID_PINK,
        STROBE,
        SOLID_WHITE,
        RAINBOW
    };

    private LED_STATE state;
    private LED_STATE prevState;

    private int deviceAddress;
    private I2C arduinoPort;

    // Sets the alliance mode on the arduino
    private static final byte SET_BLUE_MODE =   (byte) '@';
    private static final byte SET_RED_MODE =    (byte) '?';

    private static final byte ALL_OFF =         (byte) 'a';
    private static final byte SOLID_ALLIANCE =  (byte) 'b';//'?';
    private static final byte PULSE_ALLIANCE =  (byte) 'c';
    private static final byte RAINBOW =         (byte) 'd';
    private static final byte PULSE_POLICE =    (byte) 'e';
    private static final byte SOLID_GREEN =     (byte) 'f';
    private static final byte SOLID_YELLOW =    (byte) 'g';
    private static final byte SOLID_PINK =      (byte) 'h';
    private static final byte STROBE =          (byte) 'i';
    private static final byte SOLID_WHITE =     (byte) 'j';

    public ArduinoLED(int deviceAddress) {
        this.deviceAddress = deviceAddress;

        arduinoPort = new I2C(I2C.Port.kOnboard, deviceAddress);

        state = LED_STATE.OFF;
        prevState = LED_STATE.OFF;
    }

    private void sendData(byte mode) {
        arduinoPort.write(deviceAddress, mode);
    }

    // CUSTOMIZE ME
    /*
     * This is the "state machine" loop that determines what state we are in each frame
     */
    private void setLightState() {

        boolean cubeDetected = Robot.quisitor.isCubeDetected();
        boolean operatorSignalPress = Robot.oi.operatorGamepad.getRawDPadUp();

        state = LED_STATE.OFF;

        if (Robot.getInstance().isAutonomous()) {
//            state = LED_STATE.RAINBOW;
        }
        if (Robot.getInstance().isDisabled()) {
//            state = LED_STATE.PULSE_ALLIANCE;
        }

        if (cubeDetected) {
            state = LED_STATE.SOLID_GREEN;
        }
        if (operatorSignalPress) {
            state = LED_STATE.PULSE_POLICE;
        }

    }

    // CALL ME
    public void initialize() {
        // Set the proper alliance mode
        if (DriverStation.getInstance().getAlliance() == Alliance.Blue) {
            sendData(SET_BLUE_MODE);
        } else {
            sendData(SET_RED_MODE);
        }
    }

    // CALL ME
    public void periodic() {
        setLightState();
//        System.out.println("[SANITY CHECK]");

        if (prevState == state) return;

        // Send proper data to the light based on our state
        switch (state) {
            case OFF:
                sendData(ALL_OFF);
                break;
            case PULSE_ALLIANCE:
                sendData(PULSE_ALLIANCE);
                break;
            case PULSE_POLICE:
                sendData(PULSE_POLICE);
                break;
            case SOLID_ALLIANCE:
                sendData(SOLID_ALLIANCE);
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
            case STROBE:
                sendData(STROBE);
                break;
            case RAINBOW:
                sendData(RAINBOW);
                break;
        }
        prevState = state;
    }
}
