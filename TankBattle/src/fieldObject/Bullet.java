package fieldObject;

import control.CommonData;
import control.Direction;

/**
 * 砲弾を表すクラス
 * FieldObjectを継承している
 * 独自で持つ方向にしか進まない
 * @author RyotaIwasaki
 *
 */
public class Bullet extends FieldObject {

	// 一直線に飛ぶ方向
	private final Direction DIRECTION;

	public Bullet(Direction direction, int x, int y) {
		super(x, y, CommonData.BULLETNUM);
		this.DIRECTION = direction;
	}

	/**
	 * FieldObjectのメソッドをオーバーライド
	 * 一直線にしか進まないためフィールドのDIRECTIONをそのまま返す
	 */

	@Override
	protected Direction selectDirection() {
		// TODO 自動生成されたメソッド・スタブ
		return this.DIRECTION;
	}



}
