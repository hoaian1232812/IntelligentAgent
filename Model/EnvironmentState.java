package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.Environment.LocationState;

public class EnvironmentState {

	private LocationState[][] state;
	private Point agentLocation;
	public List<Point> pointDirt = new ArrayList<>();
	public List<Point> pointWall = new ArrayList<>();
	private double dirt_rate;
	private double wall_rate;
	private int m, n;

	public EnvironmentState(LocationState[][] state, double dirt_rate, double wall_rate, int m, int n) {
		this.m = m;
		this.n = n;
		this.dirt_rate = dirt_rate;
		this.wall_rate = wall_rate;
		this.agentLocation = new Point();
		this.state = state;
		createState();
	}

	public void createState() {
		int dirt = (int) (this.m * this.n * this.dirt_rate);
		int wall = (int) (this.m * this.n * this.wall_rate);

		for (int i = 0; i < this.m; i++) {
			for (int j = 0; j < this.n; j++) {
				this.state[i][j] = LocationState.CLEAN;
			}
		}

		Random r = new Random();
		for (int i = 0; i < dirt; i++) {
			int row = r.nextInt(this.m);
			int col = r.nextInt(this.n);
			if (getLocationState(row, col) == LocationState.DIRTY || getLocationState(row, col) == null) {
				i--;
			} else {
				this.state[row][col] = LocationState.DIRTY;
				pointDirt.add(new Point(row, col));
			}
		}

		for (int i = 0; i < wall; i++) {
			int row = r.nextInt(m);
			int col = r.nextInt(n);
			if (getLocationState(row, col) == null || getLocationState(row, col) == LocationState.DIRTY) {
				i--;
			} else {
				this.state[row][col] = null;
				pointWall.add(new Point(row, col));
			}
		}
	}

	public void setAgentLocation(int m, int n) {
		this.agentLocation.setLocation(m, n);
	}

	public Point getAgentLocation() {
		return this.agentLocation;
	}

	public LocationState getLocationState(int m, int n) {
		return this.state[m][n];
	}

	public void setLocationState(Point agentLocation, LocationState locationState) {
		this.state[agentLocation.x][agentLocation.y] = locationState;
	}

	public void display() {
		for (int i = 0; i < this.state.length; i++) {
			for (int j = 0; j < this.state[i].length; j++) {

				System.out.print("Environment state:  \t" + "(" + i + ", " + j + ")" + this.state[i][j] + "\t");
			}
			System.out.println();
		}
	}

}