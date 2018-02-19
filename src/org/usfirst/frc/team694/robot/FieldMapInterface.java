package org.usfirst.frc.team694.robot;

public interface FieldMapInterface {
    //Scale auton where the bot is on the same side as the scoring scale
    public double getTotalDistanceToTravelToReachNullLine();
  //public double getDistanceFromLineSensorToAutoLine();
    
    public double getAngleToReachIdealStartingPointFromNullTerritoryLine();
    public double getDistanceToTravelToReachScaleCorner();
    
    //Scale auton where the bot is on the opposite side as the scoring scale
    public double getTotalDistanceToTravelBeforeTurn();
    public double getDistanceFromLineSensorToAutoLine();
    
    public double getAngleToTurnToReachPlatformZone();
    
    public double getTotalDistanceToTravelToReachOtherSideOfPlatformZone();
    public double getDistanceToTravelToReachPlatformZone();
    public double getDistanceFromEdgeOfPlatformZoneToBump();
    public double getDistanceFromStartingPointToOtherPlatformZoneEdge();
    
    public double getAngleToTurnToReachScaleSide();
    
    public double getTotalDistanceToTravelToReachScaleSide();
    public double getDistanceFromRobotAfterTwoTurnsToNullTerritory();
    
    public double getDistanceFromBorderToScaleEdge();

    //Scale auton where the bot is on the same side as the scoring scale (backup)
    public double getTotalDistanceFromFrontOfBotToNullBump();
  //public double getDistanceFromLineSensorToAutoLine();
    public double getDistanceFromLineSensorToNullTerritoryLine();
    
    public double getAngleToTurnToReachScaleEdge();
    
    public double getDistanceToMoveBackward();
    
    public double getDistanceToReachScaleEdge();

    //Switch auton where the bot is on the same side as the scoring switch and moving forward
    public double getDistanceToDriveForwardToReachSwitchSide();
    
    //Switch auton where the bot is on the same side as the scoring switch and turning a right angle
    public double getTotalDistanceToDriveForwardToReachSwitch();
    
    public double getDistanceToDriveForwardIntoSwitchEdge();
}
