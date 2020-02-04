package tasks;

import java.util.Date;

public abstract class Task implements TaskInterface {
	protected TaskType type;
	protected String title;
	protected String description;
	protected Date date;
	
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
	
	@Override
	public TaskType getType() {
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
}