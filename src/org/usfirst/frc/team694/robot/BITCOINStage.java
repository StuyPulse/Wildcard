package org.usfirst.frc.team694.robot;

public enum BITCOINStage {
    ONE, //Acquire cube
    TWO, //Lift cube
    THREE, //Drop cube
    RESET; //Wait
    public static BITCOINStage getNextStage(BITCOINStage currentStage) {
        switch(currentStage) {
            case ONE:
                return TWO;
            case TWO:
                return THREE;
            case THREE:
                return RESET;
            default:
                return ONE;
        }
    }
}
