package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tasks.TaskType;

public class Window extends JFrame {

	public Window(int width, int height, String title) {
		super(title);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel taskLabel = new JLabel("Add new task:");
		topPanel.add(taskLabel);
		
		for (TaskType type : TaskType.values()) {
			JButton button = new JButton(type.toString());
			
			button.setFocusPainted(false);
			button.setIcon(new ImageIcon(type.getIcon()));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					openCreateTaskDialog(type);
				}
			});
			
			topPanel.add(button);
		}
		
		TaskList taskListPanel = new TaskList();
		for (int i = 0; i < 10; i++) 
			taskListPanel.addItem(new TaskListItem("Task Test: " + i));
		
		JScrollPane scrollPane = new JScrollPane(taskListPanel);
		scrollPane.setPreferredSize(new Dimension(0, 400));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
		JPanel centerPanel = new JPanel();
        
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(scrollPane, BorderLayout.NORTH);
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void openCreateTaskDialog(TaskType type) {
		switch (type) {
			case OFFICE:
				System.out.println("Create " + type.name().toLowerCase() + " task!");
				break;
			case HOME:
				System.out.println("Create " + type.name().toLowerCase() + " task!");
				break;
			case ERRANDS:
				System.out.println("Create " + type.name().toLowerCase() + " task!");
				break;
			default:
				System.out.println("Something went wrong");
				break;
		}
	}
}


