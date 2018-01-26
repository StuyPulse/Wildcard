package org.usfirst.frc.team694.robot;

/**
 * FieldMap contains the field measurements that we use in our autons.
 * All of our autons should be based on the field so that if our measurements 
 * change, we can adapt quickly. All measurements are in inches for encoder purposes.
 */
public interface FieldMap {

/* Given FRC Manual Measurements*/
    
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE = 120;
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_SWITCH = 168;
    public static final int STARTING_PLATE_HEIGHT_OF_SCALE = 60;
    public static final int HIGHEST_PLATE_HEIGHT_OF_SCALE = 72;
    public static final int LOWEST_PLATE_HEIGHT_OF_SCALE = 48;
    public static final int HEIGHT_FROM_CARPET_TO_RUNG = 84;
    public static final double HEIGHT_OF_SWITCH_FENCE = 18.75;
    
    /*null territory --> 6 ft, 3ft*/

    /* Calculated Measurement Values */
    
    
    /* Total Width - (2 * AutoLine and Alliance Station Distances) = 14ft. Divide 14ft by 2 to get 7ft.
     * 7ft + 10ft = 17ft = 204in. */
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_SCALE = 204;
    
         
}
