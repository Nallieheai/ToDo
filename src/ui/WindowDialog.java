package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import assets.DateLabelFormatter;
import tasks.Task;
import tasks.TaskType;

public class WindowDialog extends JFrame {
	
	private Window windowInstance;
	
	private TaskType dialogTypeCaller;
	private GridBagConstraints gbc;
	
	private JButton createBtn, cancelBtn;
	private JDatePickerImpl startDatePicker;
	private TitledBorder titledBorder;
	private JPanel mainPanel;
	
	public WindowDialog(Window instance) {
		super("WindowDialog");
		
		windowInstance = instance;
		
		setPreferredSize(new Dimension(300, 200));
		setLayout(new BorderLayout());
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		JLabel dateLabel = new JLabel("Date:", SwingConstants.LEFT);
		gbc.gridx = 0; 
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		mainPanel.add(dateLabel, gbc);
		
		UtilDateModel startModel = new UtilDateModel();
		Properties startProperties = new Properties();

		JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel, startProperties);
		startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
		startDatePicker.setTextEditable(false);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(2, 2, 2, 2);
		mainPanel.add(startDatePicker, gbc);
		
		createBtn = new JButton("Create task");
		createBtn.setFocusPainted(false);
		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String date = startDatePicker.getJFormattedTextField().getText();
				
				Task task = Task.createTask(dialogTypeCaller);
				task.setTitle("Test title");
				
				// windowInstance.createTask();
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(createBtn, gbc);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFocusPainted(false);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearDialog();
				setVisible(false);
			}
		});
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(cancelBtn, gbc);
		
		
		titledBorder = BorderFactory.createTitledBorder("title");
		mainPanel.setBorder(titledBorder);

		add(mainPanel, BorderLayout.NORTH);

		pack();
		setVisible(false);
	}
	
	public Task callDialog(TaskType type, Point position) {
		if (isShowing() && dialogTypeCaller == type) {
			clearDialog();
			setVisible(false);
			return null;
		} else if (isShowing() && dialogTypeCaller != type) {
			clearDialog();
		}
		
		dialogTypeCaller = type;
		setupDialog(position);
		
		return null;
	}
	
	private void clearDialog() {
		startDatePicker.getJFormattedTextField().setText("");
	}
	
	private void setupDialog(Point position) {
		setLocation(position);
		setVisible(true);
		
		titledBorder.setTitle("Creating " + dialogTypeCaller.name().toLowerCase() + " task");
		createBtn.setText("Create " + dialogTypeCaller.name().toLowerCase() + " task");
		mainPanel.repaint();
	}
}
