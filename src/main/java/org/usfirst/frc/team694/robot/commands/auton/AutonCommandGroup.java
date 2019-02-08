package org.usfirst.frc.team694.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This is to fix the "external command cancels our auton command group while it's running" problem
 * At the moment there doesn't seem to be a professional way to fix this problem (feel free to continue
 * looking at the source code for WPILIB in search of a better solution though).
 */

public abstract class AutonCommandGroup extends CommandGroup {

    public AutonCommandGroup() {
        setInterruptible(false);
    }

    @Override
    public void cancel() {
        // Cannot cancel!
    }

}
