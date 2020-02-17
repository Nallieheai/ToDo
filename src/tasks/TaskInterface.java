package tasks;

import java.time.LocalDate;

public interface TaskInterface {

	public void setTitle(String title);

	public void setDecription(String decription);
	
	public void setDate(LocalDate date);

	public String getTitle();

	public String getDecription();
	
	public LocalDate getDate();

	public TaskType getType();
	
}
