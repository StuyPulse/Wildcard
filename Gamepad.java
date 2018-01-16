package org.usfirst.frc.team694.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Class for both the Logitech Dual Action 2 Gamepad and the Logitech Gamepad
 * F310. The Logitech Gamepad F310 must have the switch on the back set to "D"
 * for this class to work. This class probably also works with the Logitech
 * Wireless Gamepad F710 (untested, but it has the exact same layout as the
 * F310).
 *
 * @author wangmeister, edited by Imperatoras and J1n
 */
public class Gamepad extends Joystick {

    public Gamepad(int port) {
        super(port);
    }

    /**
     * The left analog stick x-axis.
     *
     * @return value of left analog x-axis
     */
    public double dGetLeftX() {
        return getRawAxis(0);
    }

    /**
     * The left analog stick y-axis.
     *
     * @return value of left analog y-axis (pushing stick up is positive)
     */
    public double dGetLeftY() {
        return -getRawAxis(1);
    }

    /**
     * The right analog stick x-axis.
     *
     * @return value of right analog x-axis
     */
    public double dGetRightX() {
        return getRawAxis(2);
    }

    /**
     * The right analog stick y-axis.
     *
     * @return value of right analog y-axis (pushing stick up is positive)
     */
    public double dGetRightY() {
        return -getRawAxis(3);
    }

    /**
     * The upper d-pad button.
     *
     * @return if upper d-pad button is pressed
     */
    public boolean GetRawDPadUp() {
        return getPOV() == 0;
    }

    public dDPadButton dGetDPadUp() {
        return new dDPadButton(this, dDPadButton.Direction.UP);
    }

    /**
     * The lower d-pad button.
     *
     * @return if the lower d-pad button is pressed
     */
    public boolean GetRawDPadDown() {
        return getPOV() == 180;
    }

    public dDPadButton dGetDPadDown() {
        return new dDPadButton(this, dDPadButton.Direction.DOWN);
    }

    /**
     * The left d-pad button.
     *
     * @return if the left d-pad button is pressed
     */
    public boolean GetRawDPadLeft() {
        return getPOV() == 270;
    }

    public dDPadButton dGetDPadLeft() {
        return new dDPadButton(this, dDPadButton.Direction.LEFT);
    }

    /**
     * The right d-pad button.
     *
     * @return if the right d-pad button is pressed
     */
    public boolean GetRawDPadRight() {
        return getPOV() == 90;
    }

    public dDPadButton dGetDPadRight() {
        return new dDPadButton(this, dDPadButton.Direction.RIGHT);
    }

    /**
     * The left bumper.
     *
     * @return if the left bumper is pressed
     */
    public boolean dGetRawLeftBumper() {
        return getRawButton(5);
    }

    public JoystickButton dGetLeftBumper() {
        return new JoystickButton(this, 5);
    }

    /**
     * The right bumper.
     *
     * @return if the right bumper is pressed
     */
    public boolean dGetRawRightBumper() {
        return getRawButton(6);
    }

    public JoystickButton dGetRightBumper() {
        return new JoystickButton(this, 6);
    }

    /**
     * The left trigger.
     *
     * @return if the left trigger is pressed
     */
    public boolean dGetRawLeftTrigger() {
        return getRawButton(7);
    }

    public JoystickButton dGetLeftTrigger() {
        return new JoystickButton(this, 7);
    }

    /**
     * The right trigger.
     *
     * @return if the right trigger is pressed
     */
    public boolean dGetRawRightTrigger() {
        return getRawButton(8);
    }

    public JoystickButton dGetRightTrigger() {
        return new JoystickButton(this, 8);
    }

    /**
     * The left button of the button group. On some gamepads this is X.
     *
     * @return if the left button is pressed
     */
    public boolean dGetRawLeftButton() {
        return getRawButton(1);
    }

    public JoystickButton dGetLeftButton() {
        return new JoystickButton(this, 1);
    }

    /**
     * The bottom button of the button group. On some gamepads this is A.
     *
     * @return if the bottom button is pressed
     */
    public boolean dGetRawBottomButton() {
        return getRawButton(2);
    }

    public JoystickButton dGetBottomButton() {
        return new JoystickButton(this, 2);
    }

    /**
     * The right button of the button group. On some gamepads this is B.
     *
     * @return if the right button is pressed
     */
    public boolean dGetRawRightButton() {
        return getRawButton(3);
    }

    public JoystickButton dGetRightButton() {
        return new JoystickButton(this, 3);
    }

    /**
     * The top button of the button group. On some gamepads this is Y.
     *
     * @return if the top button is pressed
     */
    public boolean dGetRawTopButton() {
        return getRawButton(4);
    }

    public JoystickButton dGetTopButton() {
        return new JoystickButton(this, 4);
    }

    /**
     * The central left button. On some gamepads this is the select button.
     *
     * @return if the back button is pressed
     */
    public boolean dGetRawSelectButton() {
        return getRawButton(9);
    }

    public JoystickButton dGetSelectButton() {
        return new JoystickButton(this, 9);
    }

    /**
     * The central right button. On some gamepads this is the start button.
     *
     * @return if the start button is pressed
     */
    public boolean dGetRawStartButton() {
        return getRawButton(10);
    }

    public JoystickButton dGetStartButton() {
        return new JoystickButton(this, 10);
    }

    /**
     * The click-function of the left analog stick.
     *
     * @return if the left analog stick is being clicked down
     */
    public boolean dGetRawLeftAnalogButton() {
        return getRawButton(11);
    }

    public JoystickButton dGetLeftAnalogButton() {
        return new JoystickButton(this, 11);
    }

    /**
     * The click-function of the right analog stick.
     *
     * @return if the right analog stick is being clicked down
     */
    public boolean dGetRawRightAnalogButton() {
        return getRawButton(12);
    }

    public JoystickButton dGetRightAnalogButton() {
        return new JoystickButton(this, 12);
    }

    public static class dDPadButton extends Button {
        public static enum Direction {
            UP, DOWN, LEFT, RIGHT
        }

        private Gamepad gamepad;
        private Direction direction;

        public dDPadButton(Gamepad gamepad, Direction direction) {
            this.gamepad = gamepad;
            this.direction = direction;
        }

        @Override
        public boolean get() {
            switch (direction) {
            case UP:
                return gamepad.GetRawDPadUp();
            case DOWN:
                return gamepad.GetRawDPadDown();
            case LEFT:
                return gamepad.GetRawDPadLeft();
            case RIGHT:
                return gamepad.GetRawDPadRight();
            default: // Never reached
                return false;
            }
        }
    }
}
