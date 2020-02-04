package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.TitledBorder;

import tasks.Task;
import tasks.TaskType;

public class WindowDialog extends JFrame {
	
	private TaskType dialogTypeCaller;
	private GridBagConstraints gbc;
	
	public WindowDialog() {
		super("WindowDialog");
		setPreferredSize(new Dimension(300, 200));
		setLayout(new BorderLayout());
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
	
		
		
		TitledBorder title = BorderFactory.createTitledBorder("title");
		mainPanel.setBorder(title);
		

		add(mainPanel, BorderLayout.CENTER);

		pack();
		setVisible(false);
	}
	
	public Task callDialog(TaskType type, Point position) {
		if (isShowing() && dialogTypeCaller == type) {
			// Method for clearing form
			
			setVisible(false);
			return null;
		} else if (isShowing() && dialogTypeCaller != type) {
			// Method for clearing form
		}
		
		dialogTypeCaller = type;
		
		setVisible(true);
		setLocation(position);
		return null;
	}
}
