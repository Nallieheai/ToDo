package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.TitledBorder;

import tasks.TaskType;

public class WindowDialog extends JFrame {
	
	private TaskType dialogTypeCaller;
	
	public WindowDialog() {
		super("WindowDialog");
		setPreferredSize(new Dimension(300, 200));
		setLayout(new BorderLayout());
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		JPanel mainPanel = new JPanel();

		TitledBorder title1 = BorderFactory.createTitledBorder("title");
		mainPanel.setBorder(title1);

		add(mainPanel, BorderLayout.CENTER);

		pack();
		setVisible(false);
	}
	
	public void callDialog(TaskType type, Point position) {
		// System.out.println(dialogTypeCaller);
		
		
		if (isShowing() && dialogTypeCaller == type) {
			// Method for clearing form
			
			setVisible(false);
			return;
		} else if (isShowing() && dialogTypeCaller != type) {
			// Method for clearing form
		}
		
		dialogTypeCaller = type;
		
		setVisible(true);
		setLocation(position);
	}
}
