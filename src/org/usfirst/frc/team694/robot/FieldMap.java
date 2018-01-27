package org.usfirst.frc.team694.robot;

/**
 * FieldMap contains the field measurements that we use in our autons.
 * All of our autons should be based on the field so that if our measurements 
 * change, we can adapt quickly. All measurements are in inches for encoder purposes.
 */
public final class FieldMap {

/* Given FRC Manual Measurements*/
    //These distances is us starting on the left side of the field
    //all measurements in inches or degrees if specified 
    //anything with // should be changed due to the actual field measure 
    public static final double LENGTH_OF_BOT = 39.5; // (not including grabber)
    public static final double WIDTH_OF_BOT = 34.5;
    public static final double MIDDLE_OF_BOT = WIDTH_OF_BOT / 2;
    
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_NULL_TERRITORY = 288; //
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE = 120; //
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_SWITCH = 140; //
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH = 196; //
    public static final double DISTANCE_FROM_ALLIANCE_STATION_TO_PLATFORM_EDGE = 261.47; //
    public static final double DISTANCE_FROM_ALLIANCE_STATION_TO_SCALE_SIDE = 299.65; //
    public static final int DISTANCE_FROM_NULL_TERRITORY_TO_NULL_BUMP = 36; //
    public static final double DISTANCE_FROM_BORDER_TO_PLATFORM_ZONE_EDGE = 94.25; //
    public static final double DISTANCE_FROM_BORDER_TO_ROBOT_STARTING_POINT = 48 + MIDDLE_OF_BOT; //
    public static final double DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY = DISTANCE_FROM_ALLIANCE_STATION_TO_SCALE_SIDE - DISTANCE_FROM_ALLIANCE_STATION_TO_NULL_TERRITORY;
    public static final double DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_SCALE_SIDE = DISTANCE_FROM_ALLIANCE_STATION_TO_SCALE_SIDE - DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH;
    public static final int STARTING_PLATE_HEIGHT_OF_SCALE = 60;
    public static final int HIGHEST_PLATE_HEIGHT_OF_SCALE = 72;
    public static final int LOWEST_PLATE_HEIGHT_OF_SCALE = 48;
    public static final int HEIGHT_FROM_CARPET_TO_RUNG = 84;
    public static final double HEIGHT_OF_SWITCH_FENCE = 18.75;
    
    //Scale auton where the bot is on the same side as the alliance scale and bot will be in the null territory
    public static final int DISTANCE_FROM_NULL_BUMP_TO_AUTO_LINE = 204; //
    public static final double DISTANCE_FROM_BORDER_TO_SCALE_EDGE = 71.57; //
    public static final int DEGREES_TO_TURN_IN_NULL_TERRITORY = -90;
    
    //Scale auton where the bot is on the same side as the alliance scale and bot will turn at mobility line
    public static final int DISTANCE_FROM_NULL_TERRITORY_TO_AUTO_LINE = DISTANCE_FROM_ALLIANCE_STATION_TO_NULL_TERRITORY - DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE;
    public static final double DISTANCE_FROM_ROBOT_STARTING_POINT_TO_PLATFORM_ZONE_EDGE = DISTANCE_FROM_BORDER_TO_PLATFORM_ZONE_EDGE - DISTANCE_FROM_BORDER_TO_ROBOT_STARTING_POINT;
    public static final double DISTANCE_FROM_NULL_TERRITORY_TO_AUTO_LINE_SQUARED = DISTANCE_FROM_NULL_TERRITORY_TO_AUTO_LINE * DISTANCE_FROM_NULL_TERRITORY_TO_AUTO_LINE;
    public static final double DISTANCE_FROM_ROBOT_STARTING_POINT_TO_PLATFORM_ZONE_EDGE_SQUARED = DISTANCE_FROM_ROBOT_STARTING_POINT_TO_PLATFORM_ZONE_EDGE * DISTANCE_FROM_ROBOT_STARTING_POINT_TO_PLATFORM_ZONE_EDGE;
    public static final double DISTANCE_TO_TRAVEL_TO_REACH_PLATFORM_ZONE_EDGE_FROM_AUTO_LINE_SQUARED = DISTANCE_FROM_NULL_TERRITORY_TO_AUTO_LINE_SQUARED + DISTANCE_FROM_ROBOT_STARTING_POINT_TO_PLATFORM_ZONE_EDGE_SQUARED;
    public static final double DISTANCE_TO_TRAVEL_TO_REACH_PLATFORM_ZONE_EDGE = Math.sqrt(DISTANCE_TO_TRAVEL_TO_REACH_PLATFORM_ZONE_EDGE_FROM_AUTO_LINE_SQUARED);
    public static final double DEGREE_OF_ANGLE_TO_REACH_PLATFORM_ZONE_EDGE_FROM_AUTO_LINE = -1 * Math.atan(DISTANCE_FROM_ROBOT_STARTING_POINT_TO_PLATFORM_ZONE_EDGE / DISTANCE_FROM_NULL_TERRITORY_TO_AUTO_LINE);
    
    //Scale auton where the bot is on the other side of the alliance scale
    public static final int DISTANCE_FROM_AUTO_LINE_TO_FAR_SIDE_OF_SWITCH = DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH - DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE;
    public static final double DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM = DISTANCE_FROM_ALLIANCE_STATION_TO_PLATFORM_EDGE - DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH;
    public static final double DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_HALVED = DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM / 2;
    public static final double DISTANCE_TO_TRAVEL_BEFORE_FIRST_TURN_FROM_AUTO_LINE = DISTANCE_FROM_AUTO_LINE_TO_FAR_SIDE_OF_SWITCH + DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_HALVED; 
    public static final double DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_SCALE_EDGE = DISTANCE_FROM_BORDER_TO_PLATFORM_ZONE_EDGE - DISTANCE_FROM_BORDER_TO_SCALE_EDGE;
    public static final int FIRST_DEGREE_TO_TURN_ON_DIFFERENT_SIDE_SCALE_AUTON = 90;
    public static final int SECOND_GYRO_DEGREE_TO_TURN_ON_DIFFERENT_SIDE_SCALE_AUTON = -90;
    public static final double DISTANCE_TO_TRAVEL_TO_REACH_SCALE_SIDE = DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_SCALE_SIDE - DISTANCE_TO_TRAVEL_BEFORE_FIRST_TURN_FROM_AUTO_LINE;
    
    
    /*null territory --> 6 ft, 3ft*/

    /* Calculated Measurement Values */
    
    
    /* Total Width - (2 * AutoLine and Alliance Station Distances) = 14ft. Divide 14ft by 2 to get 7ft.
     * 7ft + 10ft = 17ft = 204in. */
    //public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_SCALE = 204;
    
         
}
