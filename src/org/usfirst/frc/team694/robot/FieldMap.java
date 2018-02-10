package org.usfirst.frc.team694.robot;

/**
 * FieldMap contains the field measurements that we use in our autons.
 * All of our autons should be based on the field so that if our measurements 
 * change, we can adapt quickly. All measurements are in inches for encoder purposes.
 */
public final class FieldMap {

/* Given FRC Manual Measurements*/
    //These distances is us starting on the left side of the field
    //all measurements are in inches or degrees if specified 
    //anything with // could be changed due to the actual field measure 
    
    //These are the measurements that need to be measured out before match.
    public static final double DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT = 3.75; //
    public static final double LENGTH_OF_BOT = 39.5; // (not including grabber)
    public static final double WIDTH_OF_BOT = 34.5; //
    public static final int WIDTH_OF_FIELD = 324; //
    public static final double DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_BUMP = 66.75; //
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_MIDDLE_OF_SWITCH = 168; //
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_NULL_TERRITORY = 288; //
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE = 120; //
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_SWITCH = 140; //
    public static final int DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH = 196; //
    public static final double DISTANCE_FROM_ALLIANCE_STATION_TO_PLATFORM_EDGE = 261.47; //
    public static final double DISTANCE_FROM_ALLIANCE_STATION_TO_SCALE_SIDE = 299.65; //
    public static final int DISTANCE_FROM_NULL_TERRITORY_TO_NULL_BUMP = 36; //
    public static final double DISTANCE_FROM_BORDER_TO_SWITCH_EDGE = 85.25; //
    public static final double DISTANCE_FROM_BORDER_TO_SCALE_EDGE = 71.57; //
    public static final double DISTANCE_FROM_BORDER_TO_PLATFORM_ZONE_EDGE = 95.25; //
    public static final double DISTANCE_FROM_BORDER_TO_EDGE_OF_ROBOT = 29.69; //
  //public static final int DISTANCE_FROM_NULL_BUMP_TO_AUTO_LINE = 204; //
    
    //**************************************************************************************************************
    
    public static final double DIFFERENCE_BETWEEN_LENGTH_AND_WIDTH_OF_BOT = LENGTH_OF_BOT - WIDTH_OF_BOT;
    public static final double MIDDLE_OF_BOT_WIDTHWISE = WIDTH_OF_BOT / 2;
    public static final double MIDDLE_OF_BOT_LENGTHWISE = LENGTH_OF_BOT / 2;
    
    public static final int LENGTH_OF_FIELD = WIDTH_OF_FIELD * 2;
    
    public static final double DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_ANOTHER =  DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_BUMP * 2;
    
    public static final double DISTANCE_FROM_BORDER_TO_OTHER_EDGE_OF_ROBOT = DISTANCE_FROM_BORDER_TO_EDGE_OF_ROBOT + WIDTH_OF_BOT;
    public static final double DISTANCE_FROM_BORDER_TO_ROBOT_STARTING_POINT = DISTANCE_FROM_BORDER_TO_EDGE_OF_ROBOT + MIDDLE_OF_BOT_WIDTHWISE; //
    
    public static final double DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY = DISTANCE_FROM_ALLIANCE_STATION_TO_SCALE_SIDE - DISTANCE_FROM_ALLIANCE_STATION_TO_NULL_TERRITORY;
    public static final double DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_SCALE_SIDE = DISTANCE_FROM_ALLIANCE_STATION_TO_SCALE_SIDE - DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH;
    
    public static final int STARTING_PLATE_HEIGHT_OF_SCALE = 60;
    public static final int HIGHEST_PLATE_HEIGHT_OF_SCALE = 72;
    public static final int LOWEST_PLATE_HEIGHT_OF_SCALE = 48;
    public static final int HEIGHT_FROM_CARPET_TO_RUNG = 84;
    public static final double HEIGHT_OF_SWITCH_FENCE = 18.75;
    
    public static final double DISTANCE_FROM_LINE_SENSOR_TO_AUTO_LINE = DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE - (LENGTH_OF_BOT - DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT);
   
    //**************************************************************************************************************
    //Scale auton where the bot is on the same side as the alliance scale and bot will be in the null territory
    public static final int DISTANCE_FROM_NULL_BUMP_TO_AUTO_LINE = 204;
    public static final double DISTANCE_FROM_NULL_TERRITORY_TO_AUTO_LINE = DISTANCE_FROM_ALLIANCE_STATION_TO_NULL_TERRITORY - DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE;
    public static final double DISTANCE_TO_MOVE_INTO_NULL_BUMP = MIDDLE_OF_BOT_LENGTHWISE - DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT;
    public static final double DISTANCE_TO_TRAVEL_BACKWARDS = DISTANCE_FROM_BORDER_TO_EDGE_OF_ROBOT - 2.5; 
    public static final double DISTANCE_TO_TRAVEL_TO_REACH_SCALE_EDGE = DISTANCE_FROM_BORDER_TO_SCALE_EDGE - LENGTH_OF_BOT;
    
    //Scale auton where the bot is on the same side as the alliance scale and bot will turn at null territory line
    public static final int DISTANCE_FROM_AUTO_LINE_TO_NULL_TERRITORY = DISTANCE_FROM_ALLIANCE_STATION_TO_NULL_TERRITORY - DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE;
    public static final double DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT = DISTANCE_FROM_BORDER_TO_SCALE_EDGE - DISTANCE_FROM_BORDER_TO_ROBOT_STARTING_POINT;
    public static final double DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT_SQUARED = DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT * DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT;
    public static final double DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY_SQUARED = DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY * DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY;
    public static final double DISTANCE_TO_MOVE_BACKWARD_BEFORE_TURN = DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT;
    public static final double DISTANCE_TO_TRAVEL_TO_REACH_SCALE_CORNER = Math.sqrt(DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT_SQUARED + DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY_SQUARED);
    public static final double DEGREE_OF_ANGLE_TO_REACH_IDEAL_STARTING_POINT_FROM_NULL_TERRITORY_LINE = -1 * Math.toDegrees(Math.atan(DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT / DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY));
    
    //Scale auton where the bot is on the other side of the alliance scale
    public static final int DISTANCE_FROM_AUTO_LINE_TO_FAR_SIDE_OF_SWITCH = DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH - DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE;
    public static final double DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE = DISTANCE_FROM_ALLIANCE_STATION_TO_PLATFORM_EDGE - DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH;
    public static final double DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE_HALVED = DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE / 2;
    public static final double DISTANCE_TO_TRAVEL_BEFORE_FIRST_TURN_FROM_AUTO_LINE = DISTANCE_FROM_AUTO_LINE_TO_FAR_SIDE_OF_SWITCH + DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE_HALVED + (MIDDLE_OF_BOT_LENGTHWISE - DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT); 
    public static final double DISTANCE_TO_TRAVEL_TO_REACH_PLATFORM_ZONE_EDGE = (DISTANCE_FROM_BORDER_TO_PLATFORM_ZONE_EDGE - (DISTANCE_FROM_BORDER_TO_OTHER_EDGE_OF_ROBOT + 2.5)) + DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT;
    public static final double DISTANCE_TO_DRIVE_OUT_AFTER_PLATFORM_ZONE = WIDTH_OF_BOT - DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT; 
    public static final double DISTANCE_TO_TRAVEL_TO_REACH_SCALE_SIDE = DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_SCALE_SIDE - DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE_HALVED;
    
    //**************************************************************************************************************
    /*Switch auton where the bot is in the side, on the same side as the switch, and bot has pusher
    public static final double DISTANCE_FROM_ROBOT_STARTING_POINT_TO_PLATFORM_ZONE_EDGE = DISTANCE_FROM_BORDER_TO_PLATFORM_ZONE_EDGE - DISTANCE_FROM_BORDER_TO_ROBOT_STARTING_POINT;
    public static final int FIRST_AND_SECOND_DEGREE_TO_TURN_TO_SCORE_ON_SAME_SIDE_SWITCH = 90;
    public static final double DISTANCE_TO_MOVE_INTO_PLATFORM_ZONE = WIDTH_OF_BOT;
    public static final double DISTANCE_TO_MOVE_INTO_SWITCH = DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE_HALVED - MIDDLE_OF_BOT_LENGTHWISE;

    //Switch auton where the bot is in the middle
    public static final double DISTANCE_FROM_ROBOT_SWITCH_STARTING_POINT_TO_SWITCH = 80.75;
    public static final double DISTANCE_FROM_ROBOT_SWITCH_STARTING_POINT_TO_SWITCH_SQUARED = DISTANCE_FROM_ROBOT_SWITCH_STARTING_POINT_TO_SWITCH * DISTANCE_FROM_ROBOT_SWITCH_STARTING_POINT_TO_SWITCH;
    public static final double DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_BUMP_SQUARED = DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_BUMP * DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_BUMP;
    public static final double DISTANCE_TO_TRAVEL_TO_REACH_SWITCH = Math.sqrt(DISTANCE_FROM_ROBOT_SWITCH_STARTING_POINT_TO_SWITCH_SQUARED + DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_BUMP_SQUARED);
    public static final double DEGREE_TO_TURN_TO_GET_TO_SWITCH = Math.toDegrees(-1 * Math.atan(DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_BUMP / DISTANCE_FROM_ROBOT_SWITCH_STARTING_POINT_TO_SWITCH));
    public static final double DISTANCE_TO_MOVE_FORWARD_BEFORE_DIAGONAL_TO_SWTICH = DISTANCE_FROM_ALLIANCE_STATION_TO_SWITCH - (DISTANCE_FROM_ROBOT_SWITCH_STARTING_POINT_TO_SWITCH + LENGTH_OF_BOT);

    //Switch auton where the bot is in the side, on the same side as the switch, but bot does not have pusher
    public static final double DISTANCE_FROM_AUTO_LINE_TO_MIDDLE_OF_SWITCH = DISTANCE_FROM_ALLIANCE_STATION_TO_MIDDLE_OF_SWITCH - DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE;
    public static final double DISTANCE_TO_MOVE_PAST_AUTO_LINE_TO_GET_TO_SWITCH = DISTANCE_FROM_AUTO_LINE_TO_MIDDLE_OF_SWITCH + MIDDLE_OF_BOT_LENGTHWISE;
    public static final int DEGREES_TO_TURN_TO_MIDDLE_OF_SWITCH = -90;
    public static final double DISTANCE_BOT_IS_AWAY_FROM_SWITCH_BEFORE_TURN = DISTANCE_FROM_BORDER_TO_ROBOT_STARTING_POINT + MIDDLE_OF_BOT_WIDTHWISE;
    public static final double DISTANCE_BOT_IS_AWAY_FROM_SWITCH_AFTER_TURN = DISTANCE_BOT_IS_AWAY_FROM_SWITCH_BEFORE_TURN + DIFFERENCE_BETWEEN_LENGTH_AND_WIDTH_OF_BOT;
    public static final double DISTANCE_TO_TRAVEL_TO_GET_TO_SWITCH = DISTANCE_FROM_BORDER_TO_SWITCH_EDGE - DISTANCE_BOT_IS_AWAY_FROM_SWITCH_AFTER_TURN;
    */
}
