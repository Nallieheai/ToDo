package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;

import assets.Task;

public class TaskList extends JPanel {
	private ArrayList<Task> tasks = new ArrayList<>();
	private int amountOfItems = 0;
	private GridBagConstraints gbc;
	private JPanel panel;
	
	public TaskList() {
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		add(panel, BorderLayout.NORTH);
	}
	
	public void addItem(TaskListItem item) {
		gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 0;
		gbc.gridx = 0;
		gbc.gridy = amountOfItems;
		gbc.insets = new Insets(10, 10, 10, 10);
		panel.add(item, gbc);
		amountOfItems++;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}
}
