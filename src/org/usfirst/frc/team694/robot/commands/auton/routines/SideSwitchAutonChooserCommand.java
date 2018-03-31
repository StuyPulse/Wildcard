package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class SideSwitchAutonChooserCommand extends ConditionalCommand {

    private static final double QUISITOR_DEACQUIRE_TIME = 1.5;
    private static final double QUISITOR_DEACQUIRE_SPEED = 0.8;

    public SideSwitchAutonChooserCommand() {
        super(new RightSideSwitchAutonCommand(), new LeftSideSwitchAutonCommand()); 
        System.out.println("[SideSwitch] IS RIGHT? " + Robot.isRobotStartingOnRight());
    }

    @Override
    protected boolean condition() {
        return Robot.isSwitchOnRight();
    }

    // To be used in the two switch commands: Runs the spatula deacquire for a period of time
    static class QuisitorDeacquireTimeCommand extends Command {
        public QuisitorDeacquireTimeCommand() {
            requires(Robot.quisitor);
            setTimeout(QUISITOR_DEACQUIRE_TIME);
        }

        @Override
        public void initialize() {
            Robot.quisitor.acquireSpeed(-1 * QUISITOR_DEACQUIRE_SPEED);
            System.out.println("[SideSwitchChooser.QuisitorDeacquireTimed] deacquire!");
        }

        @Override
        public void end() {
            Robot.quisitor.stop();
            System.out.println("[QuisitorDeacquireTimeCommand] done");
        }

        @Override
        protected boolean isFinished() {
            return isTimedOut();
        }
    }

}