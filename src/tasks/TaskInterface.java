package tasks;

public interface TaskInterface {

	public void setTitle(String title);

	public void setDecription(String decription);

	public String getTitle();

	public String getDecription();

	public TaskType getType();
	
}
