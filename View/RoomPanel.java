package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import Controller.Controller;
import Model.Agent;
import Model.EnvironmentState;

public class RoomPanel extends JPanel {
	private static final int SIZE = 50;

	private int width, height, m, n;
	public JButton b;
	public JLabel dirt, wall;
	private EnvironmentState evs;
	Controller c;
	private Map<Point, JLabel> map = new HashMap<>();

	public RoomPanel(int m, int n, EnvironmentState evs, Controller c) {
		this.m = m;
		this.n = n;
		this.evs = evs;
		this.c = c;
		width = this.n * SIZE;
		height = this.m * SIZE;
		setLayout(null);

		setPreferredSize(new Dimension(width, height));
		b = createRobot(40);
		add(b);

		for (int i = 0; i < this.evs.pointDirt.size(); i++) {
			dirt = createLabel(this.evs.pointDirt.get(i).y * 50 + 5, this.evs.pointDirt.get(i).x * 50 + 5, "dirt.jpg");
			map.put(new Point(this.evs.pointDirt.get(i).x, this.evs.pointDirt.get(i).y), dirt);
			add(dirt);
		}
		for (int i = 0; i < this.evs.pointWall.size(); i++) {
			wall = createLabel(this.evs.pointWall.get(i).y * 50 + 5, this.evs.pointWall.get(i).x * 50 + 5, "wall.jpg");
			add(wall);
		}

	}

	private JButton createRobot(int size) {
		Icon i = getIcon(size, "robot.jpg");
		b = new JButton();
		b.setSize(size, size);
		b.setIcon(i);
		return b;
	}

	private JLabel createLabel(int x, int y, String path) {
		Icon i = getIcon(40, path);
		dirt = new JLabel();
		dirt.setIcon(i);
		dirt.setSize(40, 40);
		dirt.setLocation(x, y);
		return dirt;
	}

	private Icon getIcon(int size, String path) {
		Image image = new ImageIcon(getClass().getResource(path)).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(size, size, image.SCALE_SMOOTH));
		return icon;
	}

	public void suckDirt(Point p) {
		if (map.containsKey(p))
			remove(map.get(p));
	}

	public void setLocationAgent(int x, int y) {
		this.b.setLocation(50 * y + 5, 50 * x + 5);
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		for (int i = 0; i <= (height / SIZE); i++) {
			g.drawLine(0, i * SIZE, width, i * SIZE);
		}
		for (int i = 0; i <= (width / SIZE); i++) {
			g.drawLine(i * SIZE, 0, i * SIZE, height);
		}
	}

}
