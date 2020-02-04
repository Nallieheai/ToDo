package tasks;

public interface TaskInterface {
	public void setTitle(String title);
	public String getTitle();
	public void setDecription(String decription);
	public String getDecription();
	public TaskType getType();
}
