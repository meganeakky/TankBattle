package control;

import java.util.ArrayList;
import java.util.List;

import field.FieldPanel;
import field.GameFrame;
import fieldObject.FieldObject;
import fieldObject.Tank;

/**
 * TankBattleの最初に動くクラス
 * Tank、フレーム、パネル、コントローラーの
 * インスタンスを生成する
 * Thread：Tankの実行
 * @author RyotaIwasaki
 *
 */
public class Starter {

	public static void main(String[] args) {

		List<FieldObject> playerList = new ArrayList<>();

		TankBattleController controller = TankBattleController.getInstance();
		Tank tank = new Tank(CommonData.PLAYER1_POINT, CommonData.PLAYER1_POINT, 1);
		playerList.add(tank);
		Tank tank2 = new Tank(CommonData.PLAYER2_POINT, CommonData.PLAYER2_POINT, 2);
		playerList.add(tank2);
		FieldPanel panel = new FieldPanel(playerList);
		GameFrame frame = new GameFrame(panel);
		controller.setPanel(panel);

		tank.start();
		tank2.start();
	}
}
