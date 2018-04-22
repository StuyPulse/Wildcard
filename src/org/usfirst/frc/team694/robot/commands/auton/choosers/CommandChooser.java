package org.usfirst.frc.team694.robot.commands.auton.choosers;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class CommandChooser extends ConditionalCommand {

    // 8 possibilities
    // 2 robot orientations
    // 2 switch orientations
    // 2 scale orientations
    public CommandChooser(
            Command switchSameScaleSame,
            Command switchSameScaleDiff,
            Command switchDiffScaleSame,
            Command switchDiffScaleDiff
            ) {

            super(
                    new CommandChooserScaleSide(switchSameScaleSame, switchSameScaleDiff),
                    new CommandChooserScaleSide(switchDiffScaleSame, switchDiffScaleDiff)
            );
        }

        @Override
        protected boolean condition() {
            return Robot.isRobotOnSameSideSwitch();
        }
    

    private static class CommandChooserScaleSide extends ConditionalCommand {
        public CommandChooserScaleSide(Command scaleSame, Command scaleDiff) {
            super(scaleSame, scaleDiff);
        }

        @Override
        protected boolean condition() {
            return Robot.isRobotOnSameSideScale();
        }
    }

}
