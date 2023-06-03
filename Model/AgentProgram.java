package Model;

import java.util.Random;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		Random r = new Random();
		if (p.getLocationState() == Environment.LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		} else {
			int rAction = r.nextInt(4);
			if (rAction == 0) {
				return Environment.MOVE_UP;
			}
			if (rAction == 1) {
				return Environment.MOVE_DOWN;
			}
			if (rAction == 2) {
				return Environment.MOVE_LEFT;
			}
			if (rAction == 3) {
				return Environment.MOVE_RIGHT;
			}
		}
		return NoOpAction.NO_OP;
	}
}