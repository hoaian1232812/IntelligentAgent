package Model;

public class Agent {
	private AgentProgram program;
	private int score = 0;

	public Agent() {
	}

	public Agent(AgentProgram aProgram) {
		program = aProgram;
	}

	public Action execute(Percept p) {
		if (program != null) {
			return program.execute(p);
		}
		return NoOpAction.NO_OP;
	}

	public int getScore() {
		return this.score;
	}

	public int setScore(int s) {
		return this.score += s;
	}
}
