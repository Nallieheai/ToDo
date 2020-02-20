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
		return type;
	}
	
	@Override
	public String getItemTitle() {
		return "["+date+": "+type+"] " + title;
	}
	
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setDecription(String decription) {
		this.description = decription;
	}

	@Override
	public String getDecription() {
		return description;
	}
	
	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}
	
	@Override
	public int compareTo(Task task) {	
		return date.compareTo(task.getDate());
	}
}