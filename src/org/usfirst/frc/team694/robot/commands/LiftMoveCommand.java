package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class LiftMoveCommand extends Command {
    double power = 1; //TODO: Figure out what power should be set to
    public LiftMoveCommand() {
        requires(Robot.lift);
    }

    protected void execute() {
        double liftControlL = Robot.oi.operatorGamepad.getLeftY();

        double liftSet = SmartDashboard.getNumber("Lift Operator setting.", power);

        if(SmartDashboard.getNumber("Lift Operator setting.", power) % 2 == 0){
          Robot.lift.move(Math.pow(liftControlL,liftSet) * Math.signum(liftControlL));
        } else {
          Robot.lift.move(Math.pow(liftControlL,liftSet));
        }


        SmartDashboard.getNumber("Lift Operator setting", power);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.lift.stop();
    }
}
