package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	

	public Window(int width, int height, String title) {
		super(title);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout());
		
		wd = new WindowDialog(this);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel taskLabel = new JLabel("Add new task:");
		topPanel.add(taskLabel);
		
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
			
			topPanel.add(button);
		}
		
		tasks = new TaskList();
		
		JScrollPane scrollPane = new JScrollPane(tasks);
		scrollPane.setPreferredSize(new Dimension(0, 400));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
		JPanel centerPanel = new JPanel();
        
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(scrollPane, BorderLayout.NORTH);
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void createTask(Task task) {
		System.out.println(task.toString());
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


