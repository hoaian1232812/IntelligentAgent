package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Model.Environment.LocationState;
import View.Observer;

public class Environment implements Observable {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false, suck = false;// all squares are CLEAN
	private Agent agent = null;
	private int m;
	private int n;
	private LocationState[][] room;
	private List<Observer> list;
	private double dirt_rate;
	private double wall_rate;

	public Environment() {
		this.list = new ArrayList<>();
	}

	public void createRoom(int m, int n, double dirt_rate, double wall_rate) {
		this.m = m;
		this.n = n;
		this.dirt_rate = dirt_rate;
		this.wall_rate = wall_rate;
		this.room = new LocationState[m][n];
		this.envState = new EnvironmentState(this.room, dirt_rate, wall_rate, m, n);
		this.agent = new Agent(new AgentProgram());
	}

	public void addAgent(int m, int n) {
		if (this.envState.getLocationState(m, n) == null) {
			System.out.println("Không thể đặt Agent ở tường");
			for (int i = 0; i < this.room.length; i++) {
				for (int j = 0; j < this.room[i].length; j++) {
					if (this.envState.getLocationState(i, j) != null) {
						this.envState.setAgentLocation(i, j);
						break;
					}

				}
			}
		} else {
			this.envState.setAgentLocation(m, n);
		}
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		if (action.equals(SUCK_DIRT)) {
			this.envState.setLocationState(this.envState.getAgentLocation(), LocationState.CLEAN);
			this.agent.setScore(500);
			suck = true;
		}
		if (action.equals(MOVE_UP)) {
			Point aLocation = this.envState.getAgentLocation();
			if (aLocation.x - 1 < 0) {
				this.agent.setScore(-10);
				return envState;
			}
			if (this.envState.getLocationState(aLocation.x - 1, aLocation.y) == null) {
				this.agent.setScore(-100);
				return envState;
			}
			this.envState.setAgentLocation(aLocation.x - 1, aLocation.y);

		}

		if (action.equals(MOVE_DOWN)) {
			Point aLocation = this.envState.getAgentLocation();
			if (aLocation.x + 1 > this.room.length - 1) {
				this.agent.setScore(-10);
				return envState;
			}
			if (this.envState.getLocationState(aLocation.x + 1, aLocation.y) == null) {
				this.agent.setScore(-100);
				return envState;
			}
			this.envState.setAgentLocation(aLocation.x + 1, aLocation.y);
		}
		if (action.equals(MOVE_RIGHT)) {
			Point aLocation = this.envState.getAgentLocation();
			if (aLocation.y + 1 > this.room[0].length - 1) {
				this.agent.setScore(-10);
				return this.envState;
			}
			if (this.envState.getLocationState(aLocation.x, aLocation.y + 1) == null) {
				this.agent.setScore(-100);
				return this.envState;
			}
			this.envState.setAgentLocation(aLocation.x, aLocation.y + 1);
		}
		if (action.equals(MOVE_LEFT)) {
			Point aLocation = this.envState.getAgentLocation();
			if (aLocation.y - 1 < 0) {
				this.agent.setScore(-10);
				return envState;
			}
			if (this.envState.getLocationState(aLocation.x, aLocation.y - 1) == null) {
				this.agent.setScore(-100);
				return envState;
			}
			this.envState.setAgentLocation(aLocation.x, aLocation.y - 1);
		}
		notifyObservers();
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		Percept p = new Percept(this.envState.getAgentLocation(),
				this.envState.getLocationState(this.envState.getAgentLocation().x, this.envState.getAgentLocation().y));
		return p;
	}

	public void step() {
		envState.display();
		Point agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocation.toString() + "\tAction: " + anAction);

		if (checkRoomClean(es))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public boolean checkRoomClean(EnvironmentState es) {
		for (int i = 0; i < this.room.length; i++) {
			for (int j = 0; j < this.room[i].length; j++) {
				if (es.getLocationState(i, j) == LocationState.DIRTY)
					return false;
			}
		}
		return true;
	}

	@Override
	public void register(Observer o) {
		// TODO Auto-generated method stub
		this.list.add(o);
	}

	@Override
	public void remove(Observer o) {
		// TODO Auto-generated method stub
		this.list.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : this.list) {
			o.update(this.envState, this.agent);
		}

	}

	public EnvironmentState getEnvState() {
		return envState;
	}

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}

	public Agent getAgent() {
		return agent;
	}

	public boolean isDone() {
		return isDone;
	}

	public boolean isSuck() {
		return suck;
	}

	public void setSuck(boolean suck) {
		this.suck = suck;
	}

	public void setM(int m) {
		this.m = m;
		notifyObservers();
	}

	public void setN(int n) {
		this.n = n;
		notifyObservers();
	}

	public double getDirt_rate() {
		return dirt_rate;
	}

	public void setDirt_rate(double dirt_rate) {
		this.dirt_rate = dirt_rate;
		notifyObservers();
	}

	public double getWall_rate() {
		return wall_rate;
	}

	public void setWall_rate(double wall_rate) {
		this.wall_rate = wall_rate;
		notifyObservers();
	}

	public LocationState[][] getRoom() {
		return room;
	}

	public void setRoom(int m, int n) {
		this.room = new LocationState[m][n];
		notifyObservers();
	}

	public void setEnvState(EnvironmentState envState) {
		this.envState = envState;
		notifyObservers();
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
		notifyObservers();
	}

}
