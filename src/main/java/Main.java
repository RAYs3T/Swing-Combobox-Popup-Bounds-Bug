import javax.swing.JComboBox;
import javax.swing.JWindow;

public class Main extends JWindow {
	public static void main(String args[]) {
		new Main();
	}

	private JComboBox<String> combo;

	public JComboBox<String> getCombo() {
		return combo;
	}

	public Main() {


		String[] values = new String[]{"Foo", "Bar"};
		combo = new JComboBox<String>(values);

		System.out.println(combo.getLocation());
		add(combo);
		setBounds(100, 100, 300, 100);
		setVisible(true);
	}
}
