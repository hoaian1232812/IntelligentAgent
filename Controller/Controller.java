package Controller;

import java.awt.Graphics;
import java.awt.Point;

import Model.Action;
import Model.Agent;
import Model.AgentProgram;
import Model.Environment;
import Model.EnvironmentState;
import Model.Environment.LocationState;
import View.MainView;

public class Controller {
	private Environment model;
	private MainView view;
	private Thread t, s1;

	public Controller() {
		super();
		this.model = new Environment();
		this.view = new MainView(model, this);
		this.view.createView();
	}

	public void addAgent(String x, String y) {
		int locx = Integer.parseInt(x);
		int locy = Integer.parseInt(y);
		this.model.addAgent(locx, locy);
		this.view.getPanel().setLocationAgent(locx, locy);
	}

	public void suckDirt() {
		if (this.model.isSuck()) {
			this.view.getPanel().suckDirt(this.model.getEnvState().getAgentLocation());
			this.model.setSuck(false);
		}
	}

	public void setUp(String row, String col, String x, String y, String dirtRate, String wallRate) {
		this.model.setM(Integer.parseInt(row));
		this.model.setN(Integer.parseInt(col));
		this.model.setDirt_rate(Double.parseDouble(dirtRate));
		this.model.setWall_rate(Double.parseDouble(wallRate));
		this.model.createRoom(this.model.getM(), this.model.getN(), this.model.getDirt_rate(),
				this.model.getWall_rate());
		this.model.setDone(false);
		this.view.update(this.model.getEnvState(), this.model.getAgent());
		this.view.addViewEvs(this.model.getM(), this.model.getN());
		addAgent(x, y);
		this.model.getAgent().setScore(0);
		stepUntilDone();
	}

	public void stepUntilDone() {
		if (s1 != null) {
			s1.stop();
		}
		s1 = new Thread(run);
		s1.start();

	}

	Runnable run = new Runnable() {

		@Override
		public void run() {
			int i = 0;
			try {
				while (!model.isDone()) {
					view.getPanel().setLocationAgent(model.getEnvState().getAgentLocation().x,
							model.getEnvState().getAgentLocation().y);
					suckDirt();
					view.setDesc();
					t.sleep(1000);
					System.out.println("step: " + i++);
					model.step();
					System.out.println("Score: " + model.getAgent().getScore());
					System.out.println("-------------------------\n");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};

}
