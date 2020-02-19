package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import tasks.Task;
import tasks.TaskType;

public class WindowDialog extends JFrame {
	
	private Window windowInstance;
	
	private TaskType dialogTypeCaller;
	
	private JButton createBtn, cancelBtn;
	private JTextArea descriptionArea;
	private JTextField titleField;
	private TitledBorder titledBorder;
	private JPanel mainPanel, btnPanel;
	private DatePicker datePicker;
	
	public WindowDialog(Window instance) {
		super("WindowDialog");
		windowInstance = instance;
		
		setPreferredSize(new Dimension(300, 250));
		setLayout(new BorderLayout());
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		/* LGoodDatePicker 10.4.1
		 * https://github.com/LGoodDatePicker/LGoodDatePicker/
		 * MIT License
		 */
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings = new DatePickerSettings();
        dateSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
        dateSettings.setFormatForDatesBeforeCommonEra("uuuu-MM-dd");
        dateSettings.setAllowKeyboardEditing(false);
        dateSettings.setVisibleClearButton(false);
        titledBorder = BorderFactory.createTitledBorder("Date");
        datePicker = new DatePicker(dateSettings);
        datePicker.setDateToToday();
		datePicker.setBorder(titledBorder);
		mainPanel.add(datePicker);
		
		// Task title field & titled border
		titledBorder = BorderFactory.createTitledBorder("Task title");
		titleField = new JTextField();
		titleField.setBorder(titledBorder);
		mainPanel.add(titleField);
		
		// Task description field & titled border
		titledBorder = BorderFactory.createTitledBorder("Task description (optional)");
		descriptionArea = new JTextArea();
		descriptionArea.setRows(2);
		descriptionArea.setBorder(titledBorder);
		mainPanel.add(descriptionArea);
		
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
				
				if (windowInstance.getTaskList().getAmountOfItems() >= windowInstance.getTaskList().getMaxAmountOfItems()) {
					// System.out.println("You cant create more than " + windowInstance.getTaskList().getMaxAmountOfItems() + " tasks");
					return;
				}

				LocalDate date = LocalDate.parse(datePicker.getText());
				String title = titleField.getText();
				
				Task task = Task.createTask(dialogTypeCaller);
				task.setTitle(title);
				task.setDate(date);
				
				if (!descriptionArea.getText().isBlank())
					task.setDecription(descriptionArea.getText());
				
				windowInstance.addTaskToList(task);
				setVisible(false);
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
		if (datePicker.getText().isBlank() ||
			titleField.getText().isBlank())
			return false;
		
		return true;	
	}
	
	public void callDialog(TaskType type, Point position) {
		if (isShowing() && dialogTypeCaller == type) {
			setVisible(false);
			return;
		}
		
		dialogTypeCaller = type;
		setupDialog(position);
		clearDialog();
	}
	
	private void clearDialog() {
		datePicker.setDateToToday();
		titleField.setText("");
		descriptionArea.setText("");
	}
	
	private void setupDialog(Point position) {
		setLocation(position);
		setVisible(true);
		
		titledBorder.setTitle("Creating " + dialogTypeCaller.name().toLowerCase() + " task");
		createBtn.setText("Create " + dialogTypeCaller.name().toLowerCase() + " task");
		mainPanel.repaint();
	}
}
