package field;

import java.util.Map;

import javax.swing.JFrame;

import control.Direction;
import fieldObject.FieldObject;
import fieldObject.Point;

/*
 * Frameの役割とコントローラーの役割を持つ
 * FieldObjectのパラメーターを表示するなどを行う
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


	public void setPanelObj(Point point, FieldObject obj) {

	}

	public Map<Direction, Integer> watchField(Point point, FieldObject obj){
		return panel.watchFieldReport(point);
	}



}
