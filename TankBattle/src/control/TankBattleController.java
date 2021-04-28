package control;

import java.util.List;
import java.util.Map;

import field.FieldPanel;
import fieldObject.Bullet;
import fieldObject.FieldObject;
import fieldObject.Tank;

/**
 * コントローラーを表すクラス
 * 各クラスの連携を行う
 *
 * @author RyotaIwasaki
 *
 */
public class TankBattleController {

	private static FieldPanel panel;
	private static final TankBattleController CONTROLLER = new TankBattleController();
	private static List<FieldObject> objList ;
	private TankBattleController() {}


	public static TankBattleController getInstance() {
		return CONTROLLER;
	}

	public static TankBattleController getInstance(List<FieldObject> list) {
		objList = list;
		return CONTROLLER;
	}


	/**
	 * Panelに対して受け取ったオブジェクト(FieldObjectを継承したもの)と
	 * そのオブジェクトが進みたい方向を渡す
	 *
	 * @param obj FieldObjectを継承したオブジェクト
	 * @param d オブジェクトが進みたい方向
	 */
	public synchronized void setObj(FieldObject obj, Direction d) {
		try {
			int damageObj = panel.setObj(obj, d);
			for(FieldObject object : objList) {
				if(object.getObjNum() == damageObj) {
					object.damage();
					if(object.getLife() == 0) {
						object.stopObj();
						panel.killObj(object);
					}
				}
			}
		} catch (Exception e) {
			System.err.println("コントローラ内でエラー");
			System.out.println(e.getMessage() + "\r\n\r\n");
			e.printStackTrace();
		}

	}

	/**
	 * FieldObjectから受け取ったインスタンスをPanelに渡し、
	 * 周囲の情報を受け取る
	 * 呼び出し側に周囲の情報を返す
	 * @param obj FieldObjectを継承したオブジェクト。主にTank
	 * @return オブジェクトの周囲の情報をMapとして呼び出し元に返す
	 */
	public synchronized Map<Direction, Boolean> aroundReport(FieldObject obj) {
		return panel.watchFieldReport(obj);

	}


	/**
	 *  Controllerで扱うPanel(フィールド)を設定する
	 * @param panel Controllerで扱うPanel(フィールド)
	 */
	public void setPanel(FieldPanel panel) {
		this.panel = panel;
	}


	public void hitBullet(int objNum) {
		for(FieldObject obj : objList) {
			if(obj.getObjNum() == objNum) {
				obj.damage();
				break;
			}
		}
	}

	public synchronized void shotBullet(Bullet bullet, Direction dire, Tank tank) {

		panel.setObj(bullet, dire, tank);
	}

}
