package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;

import assets.Task;

public class TaskList extends JPanel {
	private ArrayList<Task> tasks = new ArrayList<>();
	private GridBagConstraints gbc;
	private JPanel panel;
	
	private int amountOfItems = 0;
	private final int maxTasksAllowed = 10;
	
	public TaskList() {
		setLayout(new BorderLayout());
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		add(panel, BorderLayout.NORTH);
	}
	
	public void addItem(TaskListItem item) {
		if (amountOfItems >= maxTasksAllowed) {
			System.out.println("You may only have 10 active tasks at once, please remove one before creating another");
			return;
		}
			
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
		panel.revalidate();
		amountOfItems++;
		
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	public int getAmountOfItems() {
		return amountOfItems;
	}
}
