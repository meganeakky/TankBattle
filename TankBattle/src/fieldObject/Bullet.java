package fieldObject;

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
	private boolean isExplosion = false;



	public Bullet(Direction direction) {
		super();
		life = 1;
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


	@Override
	public void run() {
		while(!isExplosion) {
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



}
