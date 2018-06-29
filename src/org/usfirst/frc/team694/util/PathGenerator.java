package org.usfirst.frc.team694.util;

import org.usfirst.frc.team694.robot.RobotMap;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class PathGenerator {
	public double dt; 
	
	public double maxVelocity; 
	public double maxAcceleration;
	
	public Waypoint[] points;
	
	public Trajectory.Config config; 
	
	public Trajectory traj; 
	
	public TankModifier modifier; 
	public PathGenerator(Waypoint[] points, double dt, double maxVelocity, double maxAcceleration) {
		this.points = points; 
		this.config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, dt, maxVelocity, maxAcceleration, 60.0);
		traj = Pathfinder.generate(this.points, config);
		//Wheel diameter in feet
		modifier = new TankModifier(traj).modify(RobotMap.DRIVETRAIN_WHEEL_DIAMETER / 12);
	}
}
