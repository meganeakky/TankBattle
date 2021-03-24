package control;

import java.util.Map;

import field.FieldPanel;
import fieldObject.FieldObject;
import fieldObject.Point;

public class TankBattleController {

	private static FieldPanel panel;
	private TankBattleController() {}


	public static TankBattleController getInstance() {
		return new TankBattleController();
	}

	public void setObj(FieldObject obj, Direction d) {
		try {
			panel.setObj(obj, d);
		} catch (Exception e) {
			System.err.println("コントローラ内でエラー");
			System.out.println(e.getMessage() + "\r\n\r\n");
			e.printStackTrace();
		}

	}

	public synchronized Map<Direction, Integer> aroundReport(Point point) {
		return panel.watchFieldReport(point);

	}

	public void setPanel(FieldPanel panel) {
		this.panel = panel;
	}

}
