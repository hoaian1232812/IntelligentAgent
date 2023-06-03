package View;

import Model.Agent;
import Model.EnvironmentState;

public interface Observer {
	public void update(EnvironmentState evs, Agent a);
}
