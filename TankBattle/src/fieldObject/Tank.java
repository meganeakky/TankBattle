package fieldObject;

import java.util.Map;

import control.Direction;

public class Tank extends FieldObject {


	private Direction seachDirection;
	public Tank(int x, int y, int objNum) {
		super(x, y, objNum);
	}




	@Override
	protected Direction selectDirection() {
		return Direction.getRandom();
	}

	private Map<Direction, Boolean> seachAround(){
		return controller.aroundReport(this);
	}




    @Override
    public void run() {
    	try {
    		while(true) {
    			sleep(100);
    			// 索敵を表す　見つかればBulletを発射 見つからなければ移動をする
//    			seachDirection = selectDirection();


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
    				bullet.run();
    			} else {
    				super.run();
    			}
    		}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }


}
