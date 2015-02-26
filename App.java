
import javax.swing.SwingUtilities;

public class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ExampleWindow window = new ExampleWindow();
				window.setVisible(true);
			}
		});
	}
}