package org.usfirst.frc.team694.robot.commands.auton.routines;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleCubeDifferentSideScale90DegreesAutonCommand extends CommandGroup {

    public DoubleCubeDifferentSideScale90DegreesAutonCommand(boolean isRight) {
        // This command runs well with your other member of your alliance,
        // knowing Same Side Scale (90 degrees Null) Auton. Pictures can be found in Slack and Google Drive.
        addSequential(new SingleCubeDifferentSideScale90DegreesAutonCommand(isRight));
        
    }
}
