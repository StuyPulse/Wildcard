package org.usfirst.frc.team694.robot;

public interface FieldMapInterface {
    //Scale auton where the bot is on the same side as the scoring scale
    public double getDistanceFromLineSensorToAutoLine();
    public double getDistanceFromAutoLineToNullTerritory();
    public double getDistanceToMoveBackwardBeforeTurn();
    public double getDegreeOfAngleToReachIdealStartingPointFromNullTerritoryLine();
    public double getDistanceToTravelToReachScaleCorner();
    
    //Scale auton where the bot is on the opposite side as the scoring scale
    //public double getDistanceFromLineSensorToAutoLine();
    public double getDistanceToTravelBeforeFirstTurn();
    public double getDegreeOfAngleToTurnToReachPlatformZone();
    public double getDistanceToTravelToReachPlatformZoneAfterTurn();
    public double getDistanceToTravelToReachOtherPlatformZoneEdge();
    public double getDistanceToDriveOutAfterPlatformZoneEdge();
    public double getDegreeOfAngleToTurnToReachScaleSide();
    public double getDistanceToTravelToReachScaleSide();
}
