package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controller.Controller;

public class MenuView extends JPanel {
	JPanel panel, envPanel, agentLocPanel, ratePanel;
	JLabel label;
	JTextField rowText, colText, xText, yText, dirtText, wallText;
	JButton button;
	Controller control;
	private String row, col, xloc, yloc, dirtRate, wallRate;

	public MenuView(Controller control) {
		this.control = control;
		setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		envPanel = new JPanel();
		setUpEnv();
		panel.add(envPanel, BorderLayout.NORTH);
		agentLocPanel = new JPanel();
		setUpAgentLoc();
		panel.add(agentLocPanel, BorderLayout.CENTER);
		ratePanel = new JPanel();
		setUpRate();
		panel.add(ratePanel, BorderLayout.SOUTH);
		add(panel, BorderLayout.NORTH);
		button = new JButton("Xác nhận");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
		add(button, BorderLayout.SOUTH);
	}

	private void setUpEnv() {
		envPanel.setBorder(BorderFactory.createTitledBorder("Nhập kích thước môi trường"));
		envPanel.setLayout(new GridLayout(2, 3));
		label = new JLabel("Số dòng");
		envPanel.add(label);
		envPanel.add(new JLabel());
		label = new JLabel("Số cột");
		envPanel.add(label);
		envPanel.add(rowText = new JTextField(10));
		envPanel.add(new JLabel());
		envPanel.add(colText = new JTextField(10));
	}

	private void setUpAgentLoc() {
		agentLocPanel.setBorder(BorderFactory.createTitledBorder("Nhập vị trí Agent"));
		agentLocPanel.setLayout(new GridLayout(2, 3));
		label = new JLabel("X");
		agentLocPanel.add(label);
		agentLocPanel.add(new JLabel());
		label = new JLabel("Y");
		agentLocPanel.add(label);
		agentLocPanel.add(xText = new JTextField(10));
		agentLocPanel.add(new JLabel());
		agentLocPanel.add(yText = new JTextField(10));
	}

	private void setUpRate() {
		ratePanel.setBorder(BorderFactory.createTitledBorder("Nhập tỉ lệ"));
		ratePanel.setLayout(new GridLayout(2, 3));
		label = new JLabel("Bụi");
		ratePanel.add(label);
		ratePanel.add(new JLabel());
		label = new JLabel("Tường");
		ratePanel.add(label);
		ratePanel.add(dirtText = new JTextField(10));
		ratePanel.add(new JLabel());
		ratePanel.add(wallText = new JTextField(10));
	}

	private void run() {
		row = rowText.getText();
		col = colText.getText();
		xloc = xText.getText();
		yloc = yText.getText();
		dirtRate = dirtText.getText();
		wallRate = wallText.getText();
		this.control.setUp(row, col, xloc, yloc, dirtRate, wallRate);

	}

	public String getDirtRate() {
		return dirtRate;
	}

	public void setDirtRate(String dirtRate) {
		this.dirtRate = dirtRate;
	}

	public String getWallRate() {
		return wallRate;
	}

	public void setWallRate(String wallRate) {
		this.wallRate = wallRate;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getXLoc() {
		return xloc;
	}

	public void setXLoc(String x) {
		this.xloc = x;
	}

	public String getYLoc() {
		return yloc;
	}

	public void setYLoc(String y) {
		this.yloc = y;
	}

}
