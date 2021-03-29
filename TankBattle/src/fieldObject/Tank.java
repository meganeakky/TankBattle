package fieldObject;

import control.Direction;

public class Tank extends FieldObject {


	private Direction seachDirection;
	public Tank(int x, int y, int objNum) {
		super(x, y, objNum);
	}




	@Override
	protected Direction toDirection() {
		return Direction.getRandom();
	}



	private Direction searchEnemy() {
		return Direction.getRandom();

	}

    @Override
    public void run() {
    	try {
    		while(true) {
    			sleep(100);

    			// 索敵を表す　見つかればBulletを発射 見つからなければ移動をする
    			seachDirection = searchEnemy();
    			if(controller.seach(this, seachDirection)) {
    				switch (seachDirection) {
					case NORTH:
						y--;
						break;

					case SOUTH:
						y++;
						break;

					case EAST:
						x++;
						break;

					case WEST:
						x--;
						break;


					default:
						break;
					}
    				Bullet bullet = new Bullet(seachDirection, x, y);
    				controller.setObj(bullet, seachDirection);
    			} else {
    				controller.setObj(this, toDirection());
    			}
    		}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }


}
