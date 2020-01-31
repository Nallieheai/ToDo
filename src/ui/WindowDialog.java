package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.TitledBorder;

public class WindowDialog extends JFrame {
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
}
