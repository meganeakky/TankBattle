package fieldObject;

import control.Direction;

public class Tank extends FieldObject {


	public Tank(int x, int y, int objNum) {
		super(x, y, objNum);
	}



	@Override
	protected void toDirection() {
		direction = Direction.getRandom();
	}






}
