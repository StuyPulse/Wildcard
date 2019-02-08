/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team694.robot.commands.auton;

import org.usfirst.frc.team694.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ConditionalDistanceEncodersCommand extends CommandGroup {

    public ConditionalDistanceEncodersCommand(Command onTrue, double distance) {
        addSequential(new InitialDistanceEncodersCommand(distance));
        addSequential(onTrue);
    }

    private class InitialDistanceEncodersCommand extends Command {

        private double distance;

        private double startDistance;

        public InitialDistanceEncodersCommand(double distance) {
            this.distance = distance;
            requires(Robot.drivetrain);
        }

        @Override
        protected void initialize() {
            startDistance = Robot.drivetrain.getEncoderMax();
        }

        @Override
        protected boolean isFinished() {
            return Math.abs(Robot.drivetrain.getEncoderMax() - startDistance) > Math.abs(distance);
        }

    }
}
