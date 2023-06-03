package Model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Percept {
	private Point agentLocation;
	private Environment.LocationState state;

	public Percept(Point agentLocation, Environment.LocationState state) {
		this.agentLocation = agentLocation;
		this.state = state;
	}

	public Environment.LocationState getLocationState() {
		return this.state;
	}

	public Point getAgentLocation() {
		return this.agentLocation;
	}
}