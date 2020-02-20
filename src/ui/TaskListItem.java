package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tasks.Task;

public class TaskListItem extends JPanel {

	private GridBagConstraints gbc;
	private Task task;
	
	private JPanel rightPanel;
	private JButton completedBtn, editBtn, deleteBtn;
	
	private TitledBorder titledBorder;

	public TaskListItem(Task task, TaskList parent) {
		this.task = task;

		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JTextArea descriptionArea = new JTextArea(task.getDecription());
		descriptionArea.setRows(1);
		descriptionArea.setBorder(BorderFactory.createTitledBorder("Descrption (Optional)"));
		descriptionArea.setEditable(false);
		descriptionArea.setBackground(new Color(0, 0, 0, 0));

		gbc.weighty = 1;
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(4, 4, 4, 4);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		add(descriptionArea, gbc);

		rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		gbc.gridx = 1;
		gbc.gridy = 0;

		completedBtn = new JButton("Mark as completed");
		completedBtn.setFocusPainted(false);
		rightPanel.add(completedBtn);

		editBtn = new JButton("Edit");
		editBtn.setFocusPainted(false);
		rightPanel.add(editBtn);

		deleteBtn = new JButton("Delete");
		deleteBtn.setFocusPainted(false);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.removeItem(task);
			}
		});
		rightPanel.add(deleteBtn);

		add(rightPanel, gbc);
		
		LocalDate todaysDate = LocalDate.now();
		int date = todaysDate.compareTo(task.getDate());
		
		if (date > 0) {
			titledBorder = BorderFactory.createTitledBorder(task.getItemTitle() + " - Passed");
			titledBorder.setTitleColor(new Color(200, 50, 50));	
		} else if (date == 0) {
			titledBorder = BorderFactory.createTitledBorder(task.getItemTitle() + " - Today");
			titledBorder.setTitleColor(new Color(200, 100, 50));
		}
			
		setBorder(titledBorder);
	}

	public Task getTask() {
		return task;
	}
}
