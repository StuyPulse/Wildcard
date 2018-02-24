package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FlapAndFlipDownCommand extends CommandGroup {

    public FlapAndFlipDownCommand() {
        //If this is interrupted before it finishes (if the operator releases the button too fast)
        //it can break the CrabArms. This line will make it so if it starts to flip down, it will always finish.
        setInterruptible(false);

        addParallel(new CrabArmFlapOutCommand(), RobotMap.PRE_FLIP_WAIT_TIME + RobotMap.POST_FLIP_WAIT_TIME);
        addSequential(new TimedSpatulaFlipDownCommand());
    }

    @Override
    public void cancel() {
        // Uncancellable!
    }

    private class TimedSpatulaFlipDownCommand extends CommandGroup {
        public TimedSpatulaFlipDownCommand() {
            addSequential(new WaitCommand(RobotMap.PRE_FLIP_WAIT_TIME));
            addSequential(new SpatulaFlipDownCommand());
            addSequential(new WaitCommand(RobotMap.POST_FLIP_WAIT_TIME));
        }
    }
}
