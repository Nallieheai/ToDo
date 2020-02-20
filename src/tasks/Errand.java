package tasks;

public class Errand extends Task {
	
	public Errand(TaskType type) {
		this.type = type;
		System.out.println("Errand created");
	}
}
