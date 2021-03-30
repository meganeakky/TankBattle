package fieldObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import control.Direction;

public class Tank extends FieldObject {

	Map<Direction, Boolean> aroundMap = new HashMap<>();


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
    			aroundMap = seachAround();

    			List<Direction> trueDirectionList = new ArrayList<>();

    			for(Direction d : aroundMap.keySet()) {
    				if(aroundMap.get(d)) trueDirectionList.add(d);
    			}


//    			if(controller.seach(this, seachDirection)) {
    			if(trueDirectionList.size() > 0) {
        			int index = new Random().nextInt(trueDirectionList.size());
        			seachDirection = trueDirectionList.get(index);
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
    				super.commonThreadMove();
    			}
    		}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }


}
