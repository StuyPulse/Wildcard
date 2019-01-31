package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.subsystems.Lift;

/**
 *
 */
public class LiftMoveCommand extends DefaultCommand {

    private static final double GAMEPAD_LIFT_THRESHOLD = 0.1;
    private int autoComp;
    private int level;
    public LiftMoveCommand() {
        autoComp = 0;
        level = 0;
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    // TODO: This is getting out of hand
    //       Please fix DriveDistanceEncodersCommand
    @Override
    protected void defaultExecute() {

        double liftControl = Robot.oi.operatorGamepad.getLeftY();
        double liftSquared = Math.pow(liftControl, 2) * Math.signum(liftControl);
        if (level == 0){
            if (Math.abs(liftControl) > GAMEPAD_LIFT_THRESHOLD) {
                //Robot.lift.moveDangerous(liftSquared);
                if (Lift.rampDisabled) {
                    Robot.lift.moveDangerous(liftSquared);
                } else {
                    Robot.lift.moveRamp(liftSquared);
                }
//              Robot.drivetrain.enableCurrentLimit();
            } else {
                Robot.lift.stop();
//              Robot.drivetrain.disableCurrentLimit();
            }
//        System.out.println("[LiftMoveCommand] output: " + liftSquared);
        }
        setAutoComp();
        calibrateAutoComp();
        runAutoComp();
    }
    
    private void setAutoComp(){
        if (Robot.oi.operatorGamepad.getRawLeftAnalogButton() && Robot.oi.operatorGamepad.getLeftY() > 0.25){
            autoComp = 1;
        }
        
        if (Robot.oi.operatorGamepad.getRawLeftAnalogButton() && Robot.oi.operatorGamepad.getLeftY() < -0.25){
            autoComp = -1;
        }
        
        if (!Robot.oi.operatorGamepad.getRawLeftAnalogButton()){
            autoComp = 0;
            level = 0;
        }
    }
    
    private void calibrateAutoComp(){
        if (autoComp == 1 && Robot.oi.operatorGamepad.getRawLeftAnalogButton()
                && Robot.oi.operatorGamepad.getLeftY() <= 0.25 && level == 0){
            if (Robot.lift.getLiftHeight() < 5){
                level = 1;
            }else if (Robot.lift.getLiftHeight() < 10){
                level = 2;
            }else {
                level = 3;
            }
        }
        if (autoComp == -1 && Robot.oi.operatorGamepad.getRawLeftAnalogButton()
                && Robot.oi.operatorGamepad.getLeftY() <= 0.25 && level == 0){
            if (Robot.lift.getLiftHeight() > 15){
                level = 3;
            }else if (Robot.lift.getLiftHeight() > 10){
                level = 2;
            }else {
                level = 1;
            }
        }
    }
    
    private void runAutoComp(){
        if (level == 1){
            moveToNumInches(5);
        }else if (level == 2){
            moveToNumInches(10);
        }else if (level == 3){
            moveToNumInches(15);
        }
    }
    
    private void moveToNumInches(double numInches){
        if (autoComp == 1 && Robot.lift.getLiftHeight() < numInches){
            Robot.lift.moveRamp(1);
        }else if (autoComp == -1 && Robot.lift.getLiftHeight() > numInches){
            Robot.lift.moveRamp(-1);
        }else if (autoComp != 0) {
            autoComp = 0;
            level = 0;
        }
        
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }
}
