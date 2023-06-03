package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controller.Controller;
import Model.Agent;
import Model.Environment;
import Model.EnvironmentState;
import Model.Percept;

public class MainView extends JFrame implements Observer {
	private RoomPanel panel;
	private Agent a;
	private Environment model;
	private Controller control;
	private EnvironmentState evs;
	private DescriptionPanel dPanel;
	private MenuView mPanel;
	public JPanel p, p1;
	JButton b;

	public MainView(Environment model, Controller control) {
		this.model = model;
		model.register(this);
		this.control = control;

	}

	public void createView() {
		p1 = new JPanel();
		mPanel = new MenuView(this.control);
		p1.add(mPanel);
		add(p1);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void addViewEvs(int m, int n) {
		update(this.evs, this.a);
		getContentPane().remove(p1);
		p1 = new JPanel();
		mPanel = new MenuView(this.control);
		panel = new RoomPanel(m, n, this.evs, this.control);
		dPanel = new DescriptionPanel(this.a, this.evs);
		p = new JPanel();
		p.setLayout(new BorderLayout());
		p.setBackground(Color.BLACK);
		p.add(mPanel, BorderLayout.SOUTH);
		p.add(dPanel, BorderLayout.CENTER);
		p1.add(panel, BorderLayout.CENTER);
		p1.add(p, BorderLayout.EAST);
		getContentPane().add(p1);
		pack();
		getContentPane().validate();
	}

	public void addViewAgent(int x, int y) {
		this.panel.setLocationAgent(x, y);
	}

	@Override
	public void update(EnvironmentState evs, Agent a) {
		this.evs = evs;
		this.a = a;
	}

	public void setDesc() {
		this.update(this.evs, this.a);
		this.dPanel.setText();
	}

	public RoomPanel getPanel() {
		return panel;
	}

	public DescriptionPanel getdPanel() {
		return dPanel;
	}

	public MenuView getmPanel() {
		return mPanel;
	}

}
