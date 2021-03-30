package fieldObject;

import control.CommonData;
import control.Direction;

public class Bullet extends FieldObject {

	private Direction direction;
	private int x;
	private int y;

	public Bullet(Direction direction, int x, int y) {
		super(x, y, CommonData.BULLETNUM);
		this.direction = direction;
	}
	@Override
	protected Direction selectDirection() {
		// TODO 自動生成されたメソッド・スタブ
		return this.direction;
	}



}
