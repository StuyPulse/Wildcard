package org.usfirst.frc.team694.robot;

/**
 * FieldMap contains the field measurements that we use in our autons.
 * All of our autons should be based on the field so that if our measurements 
 * change, we can adapt quickly. All measurements are in inches for encoder purposes.
 */
public abstract class FieldMapMiddleFarFromScoringTableHalf implements FieldMapInterface{

/* Given FRC Manual Measurements*/
    //These distances is us starting on the left side of the field
    //all measurements are in inches or degrees if specified 
    //anything with // could be changed due to the actual field measure  
    //These are the measurements that need to be measured out before match.
    @Override
    public double getDistanceFromBorderToScaleEdge() {
        return MIDDLE_FAR_FROM_SCORING_TABLE_QUADRANT_DISTANCE_FROM_BORDER_TO_SCALE_EDGE;
    }
    final static double MIDDLE_FAR_FROM_SCORING_TABLE_QUADRANT_DISTANCE_FROM_BORDER_TO_SCALE_EDGE = 71.57; //
}
