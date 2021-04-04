package fieldObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import control.Direction;

/**
 * Tankを表すクラス
 * @author RyotaIwasaki
 *
 */
public class Tank extends FieldObject {

	private Map<Direction, Boolean> aroundMap = new HashMap<>();

	// 進みたい(砲弾を撃ちたい)方向
	private Direction seachDirection;

	public Tank(int x, int y, int objNum) {
		super(x, y, objNum);
	}


	/**
	 * FieldObjectからオーバーライド
	 * 進みたい方向をランダムで選ぶ
	 */
	@Override
	protected Direction selectDirection() {
		return Direction.getRandom();
	}


	/**
	 * 自分自身の周囲の情報をコントローラーを使いPanelから受け取る
	 * @return 周囲の情報が入ったMap
	 */
	private Map<Direction, Boolean> seachAround(){
		return controller.aroundReport(this);
	}

	/**
	 * Tank独自の動き
	 * 上下左右3マス以内に別のTankが存在すれば砲弾を発射する
	 */
    @Override
    public void run() {
    	try {
    		while(true) {
    			sleep(100);
    			// 索敵を表す 見つかればBulletを発射 見つからなければ移動をする
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
    				/*
    				 *  周囲にTankが存在しなかったため
    				 *  FieldObjectの通常動作を呼び出す
    				 */
    				super.commonThreadMove();
    			}
    		}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }


}
