package org.usfirst.frc.team694.robot;

/**
 * FieldMap contains the field measurements that we use in our autons.
 * All of our autons should be based on the field so that if our measurements 
 * change, we can adapt quickly. All measurements are in inches for encoder purposes.
 */
public final class FieldMapTopRightQuadrant implements FieldMapInterface{
  //Scale Auton where the bot is on the same side as the scoring scale and will turn on the null territory line
    @Override
    public double getDistanceFromLineSensorToAutoLine() {
        return TOP_RIGHT_QUADRANT_DISTANCE_FROM_LINE_SENSOR_TO_AUTO_LINE;
    }

    @Override
    public double getDistanceFromAutoLineToNullTerritory() {
        return TOP_RIGHT_QUADRANT_DISTANCE_FROM_AUTO_LINE_TO_NULL_TERRITORY;
    }

    @Override
    public double getDistanceToMoveBackwardBeforeTurn() {
        return TOP_RIGHT_QUADRANT_DISTANCE_TRAVEL_BACKWARDS;
    }

    @Override
    public double getDegreeOfAngleToReachIdealStartingPointFromNullTerritoryLine() {
        return TOP_RIGHT_QUADRANT_DEGREE_OF_ANGLE_TO_REACH_IDEAL_STARTING_POINT_FROM_NULL_TERRITORY_LINE;
    }

    @Override
    public double getDistanceToTravelToReachScaleCorner() {
        return TOP_RIGHT_QUADRANT_DISTANCE_TO_TRAVEL_TO_REACH_SCALE_CORNER;
    }
    
    //**************************************************************************************************************
    
    //Scale Auton where the bot is on the opposite side of the scoring scale
    
    /*    
    public double getDistanceFromLineSensorToAutoLine() {
        return TOP_RIGHT_QUADRANT_DISTANCE_FROM_LINE_SENSOR_TO_AUTO_LINE;
    }
    */
    
    @Override
    public double getDistanceToTravelBeforeFirstTurn() {
        return TOP_RIGHT_QUADRANT_DISTANCE_TO_TRAVEL_BEFORE_FIRST_TURN_FROM_AUTO_LINE;
    }

    @Override
    public double getDegreeOfAngleToTurnToReachPlatformZone() {
        return TOP_RIGHT_QUADRANT_DEGREE_OF_ANGLE_TO_TURN_TO_REACH_PLATFORM_ZONE;
    }

    @Override
    public double getDistanceToTravelToReachPlatformZoneAfterTurn() {
        return TOP_RIGHT_QUADRANT_DISTANCE_TO_TRAVEL_TO_REACH_PLATFORM_ZONE_EDGE_AFTER_TURN;
    }

    @Override
    public double getDistanceToTravelToReachOtherPlatformZoneEdge() {
        return TOP_RIGHT_QUADRANT_DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_OTHER;
    }

    @Override
    public double getDistanceToDriveOutAfterPlatformZoneEdge() {
        return TOP_RIGHT_QUADRANT_DISTANCE_TO_DRIVE_OUT_AFTER_PLATFORM_ZONE;
    }

    @Override
    public double getDegreeOfAngleToTurnToReachScaleSide() {
        return TOP_RIGHT_QUADRANT_DEGREE_OF_ANGLE_TO_TURN_TO_REACH_SCALE_SIDE;
    }

    @Override
    public double getDistanceToTravelToReachScaleSide() {
        return TOP_RIGHT_QUADRANT_DISTANCE_TO_TRAVEL_TO_REACH_SCALE_SIDE;
    }

    //**************************************************************************************************************
    
/* Given FRC Manual Measurements*/
    //These distances is us starting on the left side of the field
    //all measurements are in inches or degrees if specified 
    //anything with // could be changed due to the actual field measure 
    
    //These are the measurements that need to be measured out before match.
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_MIDDLE_OF_FIELD = 162.0; //
    public static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_BUMP = 66.75; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_MIDDLE_OF_SWITCH = 168.0; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_NULL_TERRITORY = 288.0; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE = 120.0; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_SWITCH = 140.0; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH = 196.0; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_PLATFORM_EDGE = 261.47; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_SCALE_SIDE = 299.65; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_NULL_TERRITORY_TO_NULL_BUMP = 36.0; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_SWITCH_EDGE = 85.25; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_PLATFORM_ZONE_EDGE = 95.25; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_EDGE_OF_ROBOT = 29.69; //
  //private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_NULL_BUMP_TO_AUTO_LINE = 204.0; //
    
    //**************************************************************************************************************
    
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_OTHER_EDGE_OF_ROBOT = TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_EDGE_OF_ROBOT + RobotMap.WIDTH_OF_BOT;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_ROBOT_STARTING_POINT = TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_EDGE_OF_ROBOT + FieldMap.MIDDLE_OF_BOT_WIDTHWISE; //
    
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY = TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_SCALE_SIDE - TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_NULL_TERRITORY;
    public static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_SCALE_SIDE = TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_SCALE_SIDE - TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH;

    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_LINE_SENSOR_TO_AUTO_LINE = TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE - (RobotMap.LENGTH_OF_BOT - RobotMap.DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT);
   
    //**************************************************************************************************************
    //Scale auton where the bot is on the same side as the alliance scale and bot will be in the null territory
    //Keep in case the other scale auton doesn't work
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_NULL_BUMP_TO_AUTO_LINE = 204.0; //
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_TO_MOVE_INTO_NULL_BUMP = FieldMap.MIDDLE_OF_BOT_LENGTHWISE - RobotMap.DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT;
    private static final int TOP_RIGHT_QUADRANT_DEGREE_OF_ANGLE_TO_TURN_IN_NULL_TERRITORY = 90;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_TO_TRAVEL_BACKWARDS = TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_EDGE_OF_ROBOT - FieldMap.DIFFERENCE_BETWEEN_LENGTH_OF_ROBOT_AND_WIDTH_OF_ROBOT_AFTER_TURN; 
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_TO_TRAVEL_TO_REACH_SCALE_EDGE = FieldMapBottomMiddleQuadrant.BOTTOM_MIDDLE_QUADRANT_DISTANCE_FROM_BORDER_TO_SCALE_EDGE - RobotMap.LENGTH_OF_BOT;
    
    //Scale auton where the bot is on the same side as the alliance scale and bot will turn at null territory line
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_AUTO_LINE_TO_NULL_TERRITORY = TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_NULL_TERRITORY - TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT = FieldMapBottomMiddleQuadrant.BOTTOM_MIDDLE_QUADRANT_DISTANCE_FROM_BORDER_TO_SCALE_EDGE - TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_ROBOT_STARTING_POINT;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT_SQUARED = TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT * TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY_SQUARED = TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY * TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_TRAVEL_BACKWARDS = RobotMap.DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_TO_TRAVEL_TO_REACH_SCALE_CORNER = Math.sqrt(TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT_SQUARED + TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY_SQUARED);
    private static final double TOP_RIGHT_QUADRANT_DEGREE_OF_ANGLE_TO_REACH_IDEAL_STARTING_POINT_FROM_NULL_TERRITORY_LINE = -1 * Math.toDegrees(Math.atan(TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_EDGE_TO_MIDDLE_OF_ROBOT / TOP_RIGHT_QUADRANT_DISTANCE_FROM_SCALE_SIDE_TO_NULL_TERRITORY));
    
    //Scale auton where the bot is on the other side of the alliance scale
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_AUTO_LINE_TO_FAR_SIDE_OF_SWITCH = TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH - TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_AUTO_LINE;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE = TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_PLATFORM_EDGE - TOP_RIGHT_QUADRANT_DISTANCE_FROM_ALLIANCE_STATION_TO_FAR_SIDE_OF_SWITCH;
    public static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE_HALVED = TOP_RIGHT_QUADRANT_DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE / 2;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_TO_TRAVEL_BEFORE_FIRST_TURN_FROM_AUTO_LINE = TOP_RIGHT_QUADRANT_DISTANCE_FROM_AUTO_LINE_TO_FAR_SIDE_OF_SWITCH + TOP_RIGHT_QUADRANT_DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE_HALVED + (FieldMap.MIDDLE_OF_BOT_LENGTHWISE - RobotMap.DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT); 
    private static final int TOP_RIGHT_QUADRANT_DEGREE_OF_ANGLE_TO_TURN_TO_REACH_PLATFORM_ZONE = -90;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_TO_TRAVEL_TO_REACH_PLATFORM_ZONE_EDGE_AFTER_TURN = (TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_PLATFORM_ZONE_EDGE - (TOP_RIGHT_QUADRANT_DISTANCE_FROM_BORDER_TO_OTHER_EDGE_OF_ROBOT + FieldMap.DIFFERENCE_BETWEEN_LENGTH_OF_ROBOT_AND_WIDTH_OF_ROBOT_AFTER_TURN)) + RobotMap.DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_OTHER = TOP_RIGHT_QUADRANT_DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_BUMP + FieldMapTopRightQuadrant.TOP_RIGHT_QUADRANT_DISTANCE_FROM_PLATFORM_ZONE_EDGE_TO_BUMP;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_TO_DRIVE_OUT_AFTER_PLATFORM_ZONE = RobotMap.WIDTH_OF_BOT - RobotMap.DISTANCE_LINE_SENSOR_IS_FROM_FRONT_BUMPER_OF_BOT; 
    private static final int TOP_RIGHT_QUADRANT_DEGREE_OF_ANGLE_TO_TURN_TO_REACH_SCALE_SIDE = 90;
    private static final double TOP_RIGHT_QUADRANT_DISTANCE_TO_TRAVEL_TO_REACH_SCALE_SIDE = FieldMapBottomRightQuadrant.BOTTOM_RIGHT_QUADRANT_DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_SCALE_SIDE - FieldMapBottomRightQuadrant.BOTTOM_RIGHT_QUADRANT_DISTANCE_FROM_FAR_SIDE_OF_SWITCH_TO_PLATFORM_EDGE_HALVED;
}
