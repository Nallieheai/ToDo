package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Window extends JFrame {
	
	private String tasks[] = {"Office", "Home", "Errands"};

	public Window(int width, int height, String title) {
		super(title);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel taskLabel = new JLabel("Add new task:");
		topPanel.add(taskLabel);
		
		for (String btnText : tasks) {
			JButton taskBtn = new JButton(btnText);
			taskBtn.setFocusPainted(false);
			taskBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("You have clicked " + btnText);
				}
			});
			
			topPanel.add(taskBtn);
		}
		
		TaskList taskListPanel = new TaskList();
		
		for (int i = 0; i < 10; i++) {
			taskListPanel.addItem(new TaskListItem("Task Test: " + i));
		}
		
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
}


