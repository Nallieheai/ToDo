package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import tasks.Task;

public class TaskList extends JPanel {
	private ArrayList<Task> tasks = new ArrayList<>();
	private GridBagConstraints gbc;
	private JPanel panel;
	
	private final int maxAmountOfTasksAllowed = 10;
	
	public TaskList() {
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		add(panel, BorderLayout.NORTH);
	}
	
	public void addItem(Task task) {
		if (tasks.size() >= maxAmountOfTasksAllowed) {
			System.out.println("You may only have 10 active tasks at once, please remove one before creating another");
			return;
		}
		
		tasks.add(task);
		
		sort();	
		refresh();
	}
	
	public void refresh() {
		panel.removeAll();
		
		for (int index = 0; index < tasks.size(); index++) {
			gbc = new GridBagConstraints();
			
			gbc.anchor = GridBagConstraints.NORTH;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			
			gbc.weightx = 1;
			gbc.weighty = 1;
			gbc.gridwidth = 0;
			gbc.gridx = 0;
			gbc.gridy = index;
			gbc.insets = new Insets(10, 10, 10, 10);
			
			TaskListItem item = new TaskListItem(tasks.get(index));
			
			panel.add(item, gbc);
			panel.revalidate();
		}
	} 
	
	public void sort() {
		if (tasks.size() > 1) 
			Collections.sort(tasks);
	}
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	public int getAmountOfItems() {
		return tasks.size();
	}
	
	public int getMaxAmountOfItems() {
		return maxAmountOfTasksAllowed;
	}
}
