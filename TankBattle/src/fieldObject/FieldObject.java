package fieldObject;

import control.Direction;
import control.TankBattleController;

/**
 * フィールド内に描画されるキャラクターのベースになるクラス
 * オブジェクトの情報を受け取るメソッドのほかに
 * threadとしての基本動作の意味を持つメソッドや
 * 方向を決めるための抽象メソッドを持つ
 * @author RyotaIwasaki
 *
 */
public abstract class FieldObject extends Thread {

	protected static final TankBattleController CONTROLLER = TankBattleController.getInstance();
	private final int OBJ_NUM;

	protected int x;
	protected int y;

	public FieldObject(int objNum) {
		this.OBJ_NUM = objNum;
//		this.x = x;
//		this.y = y;
	}

	/**
	 * オブジェクトのX座標を受け取るためのメソッド
	 * @return X座標を表す整数
	 */
//	public int getX() {
//		return this.x;
//	}

	/**
	 * オブジェクトのY座標を受け取るためのメソッド
	 * @return Y座標を表す整数
	 */
//	public int getY() {
//		return this.y;
//	}

	/**
	 * オブジェクト番号を受け取るためのメソッド
	 * @return
	 */
	public int getObjNum() {
		return this.OBJ_NUM;
	}

	/**
	 * 動作をするための方向を決める抽象メソッド
	 * @return ある方向を表す列挙型の値
	 */
	abstract Direction selectDirection();

	/**
	 * Threadとしての基本動作を表すメソッド
	 * 決めた方向を引数としてコントローラーを通じ
	 * Panelに反映させる
	 */
	protected void commonThreadMove() {

		CONTROLLER.setObj(this, selectDirection());

	}

	/**
	 * Threadの動作を実行する
	 */
	@Override
	public void run() {
		try {
			while (true) {
				sleep(100);
				commonThreadMove();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
