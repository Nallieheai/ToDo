package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
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
	
	private JButton createBtn, cancelBtn;
	private JDatePickerImpl startDatePicker;
	private JTextField titleField, descriptionField;
	private TitledBorder titledBorder;
	private JPanel mainPanel, btnPanel;
	
	public WindowDialog(Window instance) {
		super("WindowDialog");
		
		windowInstance = instance;
		
		setPreferredSize(new Dimension(300, 250));
		setLayout(new BorderLayout());
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		// JDatePicker & titled border
		// JDatePicker from: https://github.com/JDatePicker/JDatePicker
		UtilDateModel startModel = new UtilDateModel();
		Properties startProperties = new Properties();
		JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel, startProperties);
		startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
		titledBorder = BorderFactory.createTitledBorder("Date");
		startDatePicker.setBorder(titledBorder);
		mainPanel.add(startDatePicker);
		
		// Task title field & titled border
		titledBorder = BorderFactory.createTitledBorder("Task title");
		titleField = new JTextField();
		titleField.setBorder(titledBorder);
		mainPanel.add(titleField);
		
		// Task description field & titled border
		titledBorder = BorderFactory.createTitledBorder("Task description (optional)");
		descriptionField = new JTextField();
		descriptionField.setBorder(titledBorder);
		mainPanel.add(descriptionField);
		
		// button panel, task button & cancel button
		// FlowLayout aligned to the left, for aligning all the buttons
		// on the same row from left to right
		btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		titledBorder = BorderFactory.createTitledBorder("Task actions");
		btnPanel.setBorder(titledBorder);
		
		// Create button & action listener
		createBtn = new JButton("Create task");
		createBtn.setFocusPainted(false);
		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!validateFields()) {
					System.out.println("Please fill all of the required fields");
					return;
				}
				
				LocalDate date = LocalDate.parse(startDatePicker.getJFormattedTextField().getText());
				String title = titleField.getText();
				
				Task task = Task.createTask(dialogTypeCaller);
				task.setTitle(title);
				task.setDate(date);
				
				if (!descriptionField.getText().isBlank())
					task.setDecription(descriptionField.getText());
				
				windowInstance.addTaskToList(task);
			}
		});
		btnPanel.add(createBtn);
		
		// Cancel button & action listener
		cancelBtn = new JButton("Cancel");
		cancelBtn.setFocusPainted(false);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearDialog();
				setVisible(false);
			}
		});
		btnPanel.add(cancelBtn);
		
		titledBorder = BorderFactory.createTitledBorder("TASKS");
		mainPanel.add(btnPanel);
		mainPanel.setBorder(titledBorder);

		add(mainPanel, BorderLayout.NORTH);

		pack();
		setVisible(false);
	}
	
	private boolean validateFields() {
		if (startDatePicker.getJFormattedTextField().getText().isBlank() ||
			titleField.getText().isBlank())
			return false;
		
		return true;	
	}
	
	public void callDialog(TaskType type, Point position) {
		if (isShowing() && dialogTypeCaller == type) {
			clearDialog();
			setVisible(false);
			return;
		} else if (isShowing() && dialogTypeCaller != type) {
			clearDialog();
		}
		
		dialogTypeCaller = type;
		setupDialog(position);
	}
	
	private void clearDialog() {
		startDatePicker.getJFormattedTextField().setText("");
		titleField.setText("");
		descriptionField.setText("");
	}
	
	private void setupDialog(Point position) {
		setLocation(position);
		setVisible(true);
		
		titledBorder.setTitle("Creating " + dialogTypeCaller.name().toLowerCase() + " task");
		createBtn.setText("Create " + dialogTypeCaller.name().toLowerCase() + " task");
		mainPanel.repaint();
	}
}
