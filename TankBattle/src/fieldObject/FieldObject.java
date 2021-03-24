package fieldObject;

import control.Direction;
import control.TankBattleController;

public abstract class FieldObject extends Thread {

    TankBattleController controller;
    private int objNum;
    protected Direction direction;


    public FieldObject(int x, int y, int objNum) {
        controller = TankBattleController.getInstance();
        this.objNum = objNum;
    }


    public int getObjNum(){
        return this.objNum;
    }


    abstract void toDirection();


    @Override
    public void run() {
    	try {
			sleep(1000);
			toDirection();
	        controller.setObj(this, direction);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }

}
