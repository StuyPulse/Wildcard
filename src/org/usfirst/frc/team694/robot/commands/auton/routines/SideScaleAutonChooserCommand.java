package org.usfirst.frc.team694.robot.commands.auton.routines;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class SideScaleAutonChooserCommand extends CommandGroup {

    // Jank, but needed because we init commands based on their direction
    public enum POST_SCORE {
        NONE,
        GRAB_CUBE,
        GRAB_CUBE_AND_SCORE,
    }
    // If we're not doing anything after scoring the scale
    public SideScaleAutonChooserCommand(POST_SCORE postScore) {
        addSequential(new SingleCubeSideScaleAutonChooserCommand());
        switch (postScore) {
            case GRAB_CUBE:
                addSequential(new PostScaleGrabCubeConditionalCommand());
                break;
            case GRAB_CUBE_AND_SCORE:
                addSequential(new PostScaleGrabCubeConditionalCommand());
                addSequential(new PostScaleScoreCubeConditionalCommand());
                break;
            case NONE:
            default:
                break;
        }
    }

    // If we're not doing anything after scoring the scale
    public SideScaleAutonChooserCommand() {
        this(POST_SCORE.NONE);
    }

    // POST SCALE GRAB
    private static class PostScaleGrabCubeConditionalCommand extends ConditionalCommand {
        public PostScaleGrabCubeConditionalCommand() {
            super(new ScaleGrabCubeAfterScoringCommand(true), new ScaleGrabCubeAfterScoringCommand(false));
        }
        @Override
        protected boolean condition() {
            return Robot.isScaleOnRight();
        }
    }
    // POST SCALE SCORE
    private static class PostScaleScoreCubeConditionalCommand extends ConditionalCommand {
        public PostScaleScoreCubeConditionalCommand() {
            super(new ScaleScoreSecondTimeCommand(true), new ScaleScoreSecondTimeCommand(false));
        }
        @Override
        protected boolean condition() {
            return Robot.isScaleOnRight();
        }
    }

    private static class SingleCubeSideScaleAutonChooserCommand extends ConditionalCommand {
        public SingleCubeSideScaleAutonChooserCommand() {
            super(new SameSideScaleAutonChooserCommand(), new DifferentSideScaleAutonChooserCommand());
        }

        @Override
        protected boolean condition() {
            return Robot.isRobotAndScaleOnSameSide();
        }

        // Same Side Conditional for where the robot starts
        private static class SameSideScaleAutonChooserCommand extends ConditionalCommand {
            public SameSideScaleAutonChooserCommand() {
                super(new SameSideScaleAutonCommand(true), new SameSideScaleAutonCommand(false));
            }

            @Override
            protected boolean condition() {
                return Robot.isRobotStartingOnRight();
            }        
        }

        // Same Side Conditional for where the robot starts
        private static class DifferentSideScaleAutonChooserCommand extends ConditionalCommand {
            public DifferentSideScaleAutonChooserCommand() {
                super(new SimpleDifferentSideScaleAutonCommand(true), new SimpleDifferentSideScaleAutonCommand(false));
            }
    
            @Override
            protected boolean condition() {
                return Robot.isRobotStartingOnRight();
            }        
        }
    }
}