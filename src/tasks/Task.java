package tasks;

import java.util.Date;

public abstract class Task implements TaskInterface {
	protected TaskType type;
	protected String title;
	protected String description;
	protected Date date;
	
	public TaskType getType() {
		return type;
	}
	
	public static Task createTask(TaskType type) {
		switch (type) {
			case OFFICE:
				return new Office();
			case HOME:
				return new Home();
			case ERRANDS:
				return new Errand();
			default:
				return new Office();
		}
	}
}