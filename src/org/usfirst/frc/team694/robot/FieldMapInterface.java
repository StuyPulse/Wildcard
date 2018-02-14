package org.usfirst.frc.team694.robot;

public interface FieldMapInterface {
    //Scale auton where the bot is on the same side as the scoring scale
    public double getTotalDistanceToTravelToReachNullLine();
  //public double getDistanceFromLineSensorToAutoLine();
    
    public double getDegreeOfAngleToReachIdealStartingPointFromNullTerritoryLine();
    public double getDistanceToTravelToReachScaleCorner();
    
    //Scale auton where the bot is on the opposite side as the scoring scale
    public double getTotalDistanceToTravelBeforeTurn();
    public double getDistanceFromLineSensorToAutoLine();
    
    public double getDegreeOfAngleToTurnToReachPlatformZone();
    
    public double getTotalDistanceToTravelToReachOtherSideOfPlatformZone();
    public double getDistanceToTravelToReachPlatformZone();
    public double getDistanceFromStartingPointToOtherPlatformZoneEdge();
    
    public double getDegreeOfAngletoTurnToReachScaleSide();
    
    public double getTotalDistanceToTravelToReachScaleSide();
    public double getDistanceFromRobotAfterTwoTurnsToNullTerritory();
}
