package tasks;

import java.time.LocalDate;

public abstract class Task implements TaskInterface, Comparable<Task> {
	protected TaskType type;
	protected String title;
	protected String description;
	protected LocalDate date;
	
	public static Task createTask(TaskType type) {
		switch (type) {
			case OFFICE:
				return new Office(type);
			case HOME:
				return new Home(type);
			case ERRANDS:
				return new Errand(type);
			default:
				return null;
		}
	}
	
	@Override
	public TaskType getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title = title;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public void setDecription(String decription) {
		// TODO Auto-generated method stub
		this.description = decription;
	}

	@Override
	public String getDecription() {
		// TODO Auto-generated method stub
		return description;
	}
	
	@Override
	public void setDate(LocalDate date) {
		// TODO Auto-generated method stub
		this.date = date;
	}

	@Override
	public LocalDate getDate() {
		// TODO Auto-generated method stub
		return date;
	}
	
	@Override
	public int compareTo(Task task) {	
		return date.compareTo(task.getDate());
	}
}