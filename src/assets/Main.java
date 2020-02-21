package assets;

import javax.swing.SwingUtilities;

import ui.Window;

public class Main {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Window(800, 500, "ToDo Tasks");
			}
		});
	}
}
