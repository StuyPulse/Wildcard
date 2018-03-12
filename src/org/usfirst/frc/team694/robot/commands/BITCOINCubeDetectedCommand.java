package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class BITCOINCubeDetectedCommand extends ConditionalCommand {

    public BITCOINCubeDetectedCommand() {
        super(new BITCOINCommand(), new CrabArrowAcquireUntilAcquiredCommand());
    }
    
    @Override
    public boolean condition() {
        return Robot.quisitor.isCubeDetected();
    }
}
