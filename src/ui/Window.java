package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tasks.Task;
import tasks.TaskType;

public class Window extends JFrame {
	private WindowDialog wd;
	private TaskList tasks;

	private JPanel topPanel, innerLeftTopPanel, centerPanel, statusPanel;
	private JLabel taskLabel, amountOfItemsLabel, statusLabel;
	private JScrollPane scrollPane;

	public Window(int width, int height, String title) {
		super(title);

		// Window settings

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout());

		// Initialize window dialog to be used later
		wd = new WindowDialog(this);

		// Top Panel (task buttons + amount of tasks label)
		topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 10));
		innerLeftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		taskLabel = new JLabel("Add new task:");
		innerLeftTopPanel.add(taskLabel);

		for (TaskType type : TaskType.values()) {
			JButton button = new JButton(type.toString());

			button.setFocusPainted(false);
			button.setIcon(new ImageIcon(type.getIcon()));
			button.setMargin(new Insets(2, 6, 2, 6));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int xPos = button.getLocationOnScreen().x - button.getMargin().left;
					int yPos = (int) (button.getLocationOnScreen().y + button.getSize().getHeight());
					Point position = new Point(xPos, yPos);
					openCreateTaskDialog(type, position);
				}
			});

			innerLeftTopPanel.add(button);
		}

		// Center Panel (Task List)

		tasks = new TaskList(this);
		amountOfItemsLabel = new JLabel(
				"Tasks in the list: " + tasks.getAmountOfItems() + "/" + tasks.getMaxAmountOfItems());
		amountOfItemsLabel.setForeground(new Color(200, 50, 50));

		topPanel.add(innerLeftTopPanel, BorderLayout.WEST);
		topPanel.add(amountOfItemsLabel, BorderLayout.EAST);

		scrollPane = new JScrollPane(tasks);
		scrollPane.getVerticalScrollBar().setUnitIncrement(4);
		scrollPane.setPreferredSize(new Dimension(0, 400));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(scrollPane, BorderLayout.NORTH);

		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);

		// Status panel (Latest action performed)

		statusPanel = new JPanel(new BorderLayout());
		statusPanel.setPreferredSize(new Dimension(0, 20));
		statusPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 10));
		statusLabel = new JLabel("Placeholder Status Text");
		statusLabel.setForeground(new Color(200, 50, 50));
		statusPanel.add(statusLabel, BorderLayout.EAST);

		add(statusPanel, BorderLayout.SOUTH);

		// Window settings

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void updateAmountOfItemsLabel() {
		amountOfItemsLabel
				.setText("Tasks in the list: " + tasks.getAmountOfItems() + "/" + tasks.getMaxAmountOfItems());
	}

	public void updateStatusLabel(String status) {
		statusLabel.setText(status);
	}

	public TaskList getTaskList() {
		return tasks;
	}

	public void addTaskToList(Task task) {
		tasks.addItem(task);
		updateAmountOfItemsLabel();
	}

	public void openCreateTaskDialog(TaskType type, Point position) {
		switch (type) {
		case OFFICE:
			wd.callDialog(type, position);
			break;
		case HOME:
			wd.callDialog(type, position);
			break;
		case ERRANDS:
			wd.callDialog(type, position);
			break;
		default:
			System.out.println("Something went wrong");
			break;
		}
	}
}
