package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import tasks.Task;

public class TaskListItem extends JPanel {
	
	private GridBagConstraints gbc;
	private Task task;
	
	public TaskListItem(Task task) {
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel titleLabel = new JLabel("Date: " + task.getDate().toString() + " | " + task.getType() + " | " + task.getTitle());
		
		gbc.weighty = 1;
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0; 
		
		add(titleLabel, gbc);
		
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		gbc.gridx = 1;
		gbc.gridy = 0;

		JButton completedBtn = new JButton("Mark as completed");
		completedBtn.setFocusPainted(false);
		rightPanel.add(completedBtn);
		
		JButton editBtn = new JButton("Edit");
		editBtn.setFocusPainted(false);
		rightPanel.add(editBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setFocusPainted(false);
		rightPanel.add(deleteBtn);
		
		add(rightPanel, gbc);
		setBorder(new LineBorder(Color.BLACK, 1)); 
	}
	
	public Task getTask() {
		return task;
	}
}
