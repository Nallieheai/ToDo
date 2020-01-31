package tasks;

public enum TaskType {
	OFFICE	(System.getProperty("user.dir") + "\\imgs\\icons\\office.png"),
	HOME	(System.getProperty("user.dir") + "\\imgs\\icons\\home.png"),
	ERRANDS	(System.getProperty("user.dir") + "\\imgs\\icons\\errands.png");
	
	private String m_Icon;

	TaskType(String icon) {
		m_Icon = icon;
	}

	public String getIcon() {
		return m_Icon;
	}
	
	@Override
	public String toString() {
		return name().substring(0, 1) + name().toLowerCase().substring(1);
	}
}

