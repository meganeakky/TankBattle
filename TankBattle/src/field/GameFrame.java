package field;

import javax.swing.JFrame;

/**
 * Panelを表示させるFrame
 * @author RyotaIwasaki
 *
 */
public class GameFrame extends JFrame {


	private FieldPanel panel;

	private static final int FRAME_LENGTH = 1000;

	public GameFrame(FieldPanel panel) {

		this.panel = panel;
		setContentPane(this.panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(FRAME_LENGTH, FRAME_LENGTH);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}

}
