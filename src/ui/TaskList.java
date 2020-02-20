package ui;

import java.awt.BorderLayout;
import java.awt.Component;
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

	private Window window;

	private final int maxAmountOfTasksAllowed = 10;

	public TaskList(Window window) {
		this.window = window;
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

		window.updateStatusLabel("New " + task.getType() + " task added: " + task.getItemTitle());
	}

	public void removeItem(Task taskToRemove) {
		tasks.remove(taskToRemove);
		refresh();

		window.updateAmountOfItemsLabel();
		window.updateStatusLabel("Task deleted: " + taskToRemove.getItemTitle());
	}
	
	public void cancelEditOnItems() {
		for (Component component : panel.getComponents()) {
			TaskListItem item = (TaskListItem) component;
			if (item.isBeingEdited())
				item.editItem();
		}
	}

	public void refresh() {
		if (panel.getComponentCount() > 0)
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

			TaskListItem item = new TaskListItem(tasks.get(index), this);

			panel.add(item, gbc);
		}

		panel.revalidate();
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
