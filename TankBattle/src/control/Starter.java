package control;

import field.FieldPanel;
import field.GameFrame;
import fieldObject.Tank;

public class Starter {

	public static void main(String[] args) {

		TankBattleController controller = TankBattleController.getInstance();
		Tank tank = new Tank(CommonData.PLAYER1_POINT, CommonData.PLAYER1_POINT, 1);
		FieldPanel panel = new FieldPanel(tank);
		GameFrame frame = new GameFrame(panel);
		controller.setPanel(panel);




		tank.start();
	}
}
