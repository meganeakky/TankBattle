package fieldObject;

import control.Direction;
import control.TankBattleController;

public abstract class FieldObject extends Thread {

    TankBattleController controller;
    private int objNum;
    protected Direction direction;

    protected int x;
    protected int y;


    public FieldObject(int x, int y, int objNum) {
        controller = TankBattleController.getInstance();
        this.objNum = objNum;
        this.x = x;
        this.y = y;
    }




    public int getX() {
		return x;
	}




	public int getY() {
		return y;
	}




	public int getObjNum(){
        return this.objNum;
    }


    abstract Direction toDirection();




}
