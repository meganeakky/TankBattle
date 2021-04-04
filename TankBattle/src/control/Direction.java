package control;

import java.util.Random;

/**
 * オブジェクトの上下左右を表す列挙型
 * @author RyotaIwasaki
 *
 */
public enum Direction {

	NORTH,
	SOUTH,
	EAST,
	WEST;

	/**
	 * 上下左右の中からランダムで1つを選択し戻り値として返す
	 * @return
	 */
	public static Direction getRandom() {
		int rand = new Random().nextInt(Direction.values().length);
		return Direction.values()[rand];
	}
}
