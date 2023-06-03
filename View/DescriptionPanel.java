package View;

import java.awt.Dimension;

import javax.swing.*;

import Model.Agent;
import Model.EnvironmentState;
import Model.Percept;

public class DescriptionPanel extends JPanel {
	private Agent a;
	private EnvironmentState evs;
	private String t;
	private JLabel text;

	public DescriptionPanel(Agent a, EnvironmentState evs) {

		this.a = a;
		this.evs = evs;
		text = new JLabel(t);
		add(text);
	}

	public void setText() {

		t = "L: (" + this.evs.getAgentLocation().x + ", " + this.evs.getAgentLocation().y + ") "
				+ this.evs.getLocationState(this.evs.getAgentLocation().x, this.evs.getAgentLocation().y) + "   Score: "
				+ this.a.getScore();
		this.text.setText(t);
	}

}
