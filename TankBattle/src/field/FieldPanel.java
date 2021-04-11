package field;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import control.CommonData;
import control.Direction;
import fieldObject.Bullet;
import fieldObject.FieldObject;
import fieldObject.Tank;

/**
 * TankBattleのフィールドを表すクラス
 * フィールドの大きさを二次元配列で表現しており
 * 各メソッドによって整数を動かすことで
 * 各オブジェクトの動きを表現する
 * @author RyotaIwasaki
 *
 */
public class FieldPanel extends JPanel {

	/*
	 *  フィールドの座標を表す二次元配列
	 *  0:空
	 *  1～4:Tank(予定)
	 *  5:Bullet
	 *  9:壁
	 */
	private int[][] field = {
			// 8 * 8の升目0は空、1が戦車、2が砲弾を表す
			{ 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 }
	};

	private static final int BLANK = 0;
	private static Map<Integer, Boolean> damageMap = new HashMap<>();

	/**
	 * コンストラクタ
	 * 初期化時にフィールド内で動かすＴａｎｋを設定する
	 * ※FieldObjectを渡す必要はないのではないか。コントローラーに渡すべき？
	 * @param objs Panelの初期化時に入れておきたいTankのList
	 */
	public FieldPanel(List<FieldObject> objs) {

		for (FieldObject obj : objs) {
			switch (obj.getObjNum()) {
			case CommonData.PLAYER1_NUM:
				field[CommonData.PLAYER1_POINT][CommonData.PLAYER1_POINT] = obj.getObjNum();
				break;
			case CommonData.PLAYER2_NUM:
				field[CommonData.PLAYER2_POINT][CommonData.PLAYER2_POINT] = obj.getObjNum();
				break;

			default:
				break;
			}
		}
		for(int i = 1; i < 5; i ++) {
			damageMap.put(i, false);
		}

	}

	/**
	 * field内から受け取ったオブジェクト(正確にはオブジェクト番号)を探し、
	 * 受け取った方向に動かす。
	 * 動かした先に壁、もしくはほかのオブジェクトが存在すれば動かさない
	 * @param obj 動かしたいオブジェクト
	 * @param dire 動かしたい方向
	 */
	public synchronized Map setObj(FieldObject obj, Direction dire) {

		// 受け取ったオブジェクトからオブジェクト番号を受け取る

		int toX = 0;
		int toY = 0;
		int currentX = 0;
		int currentY = 0;

		try {
			A: for (int x = 0; x < field.length; x++) {
				for (int y = 0; y < field[x].length; y++) {
					if (field[x][y] == obj.getObjNum()) {
						currentX = x;
						currentY = y;
						switch (dire) {

						case NORTH:
							toY += (currentY - 1);
							toX = currentX;
							break A;

						case SOUTH:
							toY += (currentY + 1);
							toX = currentX;
							break A;

						case EAST:
							toX += (currentX + 1);
							toY = currentY;
							break A;

						case WEST:
							toX += (currentX - 1);
							toY = currentY;
							break A;
						}
					}
				}

			}
			if (toX == 0 || toX == 9 || toY == 0 || toY == 9) {
				repaint(1000, currentX * 100, currentY * 100, 100, 100);
			} else if (field[toX][toY] == 1 || field[toX][toY] == 2 || field[toX][toY] == 5) {
				if (obj instanceof Tank && field[toX][toY] == 5) {
					// objNumに応じてTankに対してダメージの宣言を行う
					repaint(1000, currentX * 100, currentY * 100, 100, 100);
					isDamage = false;

				} else if (field[toX][toY] == 5) {
					// Bullet同士を消滅させる

				} else if (field[toX][toY] == 5) {
					// Bullet同士を消滅させる

				} else {
					repaint(1000, currentX * 100, currentY * 100, 100, 100);
				}

			} else {

				field[toX][toY] = obj.getObjNum();
				field[currentX][currentY] = BLANK;
				//repaint(1000, toX*100, toY*100, toX*100 - currentX*100, toY*100 - currentY*100);
				repaint();
			}

			// その番号を2次元配列から探す

		} catch (Exception e) {
			System.out.println(e.getMessage() + "\r\n\r\n");
			e.printStackTrace();
		}
		return isDamage;

	}

	public synchronized void setObj(Bullet bullet, Direction dire, Tank tank) {
		int toX = 0;
		int toY = 0;
		A: for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {
				if (field[x][y] == tank.getObjNum()) {
					switch (dire) {

					case NORTH:
						toY += (y - 1);
						toX = x;
						break A;

					case SOUTH:
						toY += (y + 1);
						toX = x;
						break A;

					case EAST:
						toX += (x + 1);
						toY = y;
						break A;

					case WEST:
						toX += (x - 1);
						toY = y;
						break A;
					}
				}
			}
		}
		field[toX][toY] = bullet.getObjNum();
	}

	/**
	 * 描画を表すメソッド
	 * Field内の整数に応じで色・形を変化させる
	 *
	 */
	public void paint(Graphics g) {
		super.paint(g);

		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {

				switch (field[x][y]) {
				// 壁
				case 9:
					g.setColor(Color.BLACK);
					g.fillRect(x * 100, y * 100, 100, 100);
					break;

				// Tankプレイヤー1
				case 1:
					g.setColor(Color.GREEN);
					g.fillRect(x * 100, y * 100, 100, 100);
					break;

				// Tank プレイヤー2
				case 2:
					g.setColor(Color.ORANGE);
					g.fillRect(x * 100, y * 100, 100, 100);
					break;

				// Bullet
				case 5:
					g.setColor(Color.RED);
					g.fillOval(x * 100, y * 100, 100, 100);
					break;

				default:
					break;
				}

			}
		}
	}

	/**
	 * 受け取ったオブジェクトがField内の度の座標にいるか確認し
	 * そのオブジェクトの上下左右3マスの中に別のオブジェクトが存在するか判定
	 * 結果を戻り値として変えす
	 * @param obj FieldObjectを継承したオブジェクト
	 * @return aroundMap 	オブジェクトの上下左右に別オブジェクトが存在するかの判定結果
	 * 						方向をキー、Boolean値をバリューとしたMap
	 */
	public synchronized Map<Direction, Boolean> watchFieldReport(FieldObject obj) {
		Map<Direction, Boolean> aroundMap = new HashMap<>();

		boolean tankInEast = false;
		boolean tankInWest = false;
		boolean tankInNorth = false;
		boolean tankInSouth = false;

		// オブジェクトの座標探し
		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {
				if (field[x][y] == obj.getObjNum()) {

					// 上下左右3マスずつ別オブジェクトの存在を判定
					for (int n = 1; n < 4; n++) {
						if ((x + n) < 9 && field[x + n][y] > 0 && field[x + n][y] < 5) {
							tankInEast = true;
						}
						if ((x - n) > 0 && field[x - n][y] > 0 && field[x - n][y] < 5) {
							tankInWest = true;
						}
						if ((y + n) < 9 && field[x][y + n] > 0 && field[x][y + n] < 5) {
							tankInSouth = true;
						}
						if ((y - n) > 0 && field[x][y - n] > 0 && field[x][y - n] < 5) {
							tankInNorth = true;
						}
					}
				}
			}
		}
		aroundMap.put(Direction.EAST, tankInEast);
		aroundMap.put(Direction.WEST, tankInWest);
		aroundMap.put(Direction.NORTH, tankInNorth);
		aroundMap.put(Direction.SOUTH, tankInSouth);

		return aroundMap;
	}

	private void iniDamageMap(){
		for(Integer key : damageMap.keySet()) {
			damageMap.replace(key, false);
		}
	}

}
