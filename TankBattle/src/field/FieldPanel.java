package field;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import control.Direction;
import fieldObject.FieldObject;
import fieldObject.Point;



public class FieldPanel extends JPanel {

	private int[][] field ={
			// 8 * 8の升目0は空、1が戦車、2が砲弾を表す
			{9,9,9,9,9,9,9,9,9,9},
			{9,0,0,0,0,0,0,0,0,9},
			{9,0,0,0,0,0,0,0,0,9},
			{9,0,0,0,0,0,0,0,0,9},
			{9,0,0,0,0,0,0,0,0,9},
			{9,0,0,0,0,0,0,0,0,9},
			{9,0,0,0,0,0,0,0,0,9},
			{9,0,0,0,0,0,0,0,0,9},
			{9,0,0,0,0,0,0,0,0,9},
			{9,9,9,9,9,9,9,9,9,9}
		};

	private static final int BLANK = 0;
	private static final int PANELSIZE = 1000;
	private static final String IMAGE_PATH = "C:/Users/akkym/OneDrive/画像/Java/TankBattle/Resized/TankImage.jpg";

	private BufferedImage bImage = null;
	private int x = 0;
	private int y = 0;
	private Image tankImage;

	public FieldPanel(FieldObject obj) {

		try{
			bImage = ImageIO.read(new File(IMAGE_PATH));

		}catch(Exception e) {
			e.printStackTrace();
		}
		field[1][1] = obj.getObjNum();
	}



	public synchronized void setObj(FieldObject obj, Direction dire) {

		// 受け取ったオブジェクトからオブジェクト番号を受け取る

		int toX = 0;
		int toY = 0;

		try {

			while(true) {
				for(int x = 0; x < field.length; x++) {
					for(int y = 0; y < field[x].length; y++) {
						if(field[x][y] == obj.getObjNum()) {
							toX = x;
							toY = y;
							switch(dire) {

								case NORTH:
									toY--;
									break;

								case SOUTH:
									toY++;
									break;

								case EAST:
									toX++;
									break;

								case WEST:
									toX--;
									break;
							}
						}
					}
				}
				if( x > 0 && x < 9 && y > 0 && y < 9 ) {
					field[toX][toY] = obj.getObjNum();
					field[x][y] = BLANK;
					repaint(1000, toX, toY, 500, 500);
				} else {
					repaint(1000, x, y, 500, 500);
				}

			}
			// その番号を2次元配列から探す

		}catch (Exception e) {
			System.out.println(e.getMessage() + "\r\n\r\n");
			e.printStackTrace();
		}
	}



	public void paint(Graphics g) {
		super.paint(g);

		for(int x = 0; x < field.length; x++) {
			for(int y = 0; y < field[x].length; y++) {

				switch (field[x][y]) {
				case 9:
					g.setColor(Color.BLACK);
					g.fillRect(x * 100, y * 100, 100, 100);
					break;

				case 1:
					g.setColor(Color.GREEN);
					g.fillRect(x * 100, y * 100, 100, 100);
					break;

				case 2:
					g.setColor(Color.ORANGE);
					g.fillRect(x * 100, y * 100, 100, 100);
					break;


				default:
					break;
				}

			}
		}



	}



	public Map<Direction, Integer> watchFieldReport(Point point){
		Map<Direction, Integer> aroundMap = new HashMap<>();

		aroundMap.put(Direction.SOUTH, field[point.getX()][point.getY() - 1]);
		aroundMap.put(Direction.SOUTH, field[point.getX()][point.getY() + 1]);
		aroundMap.put(Direction.EAST, field[point.getX() + 1][point.getY()]);
		aroundMap.put(Direction.WEST, field[point.getX() - 1][point.getY()]);
		return aroundMap;
	}



}
