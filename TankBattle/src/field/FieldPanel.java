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

	private int[][] field = {
			// 8 * 8の升目0は空、1が戦車、2が砲弾を表す
			{ 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 }
	};

	private static final int BLANK = 0;
	private static final int PANELSIZE = 1000;
	private static final String IMAGE_PATH = "C:/Users/akkym/OneDrive/画像/Java/TankBattle/Resized/TankImage.jpg";

	private BufferedImage bImage = null;
	private int currentX = 0;
	private int currentY = 0;
	private Image tankImage;

	public FieldPanel(FieldObject obj) {

		try {
			bImage = ImageIO.read(new File(IMAGE_PATH));

		} catch (Exception e) {
			e.printStackTrace();
		}
		field[1][1] = obj.getObjNum();
	}

	public synchronized void setObj(FieldObject obj, Direction dire) {

		// 受け取ったオブジェクトからオブジェクト番号を受け取る

		int toX = 0;
		int toY = 0;

		try {

			A: for (int x = 0; x < field.length; x++) {
				for (int y = 0; y < field[x].length; y++) {
					if (field[x][y] == obj.getObjNum()) {
						currentX = x;
						currentY = y;
						switch (dire) {

						case NORTH:
							toY += (currentY - 1);
							toX = currentX;
							break A;

						case SOUTH:
							toY += (currentY + 1);
							toX = currentX;
							break A;

						case EAST:
							toX += (currentX + 1);
							toY = currentY;
							break A;

						case WEST:
							toX += (currentX - 1);
							toY = currentY;
							break A;
						}
					}
				}

			}
			if (toX > 0 && toX < 9 && toY > 0 && toY < 9) {
				field[toX][toY] = obj.getObjNum();
				field[currentX][currentY] = BLANK;
//				repaint(1000, toX*100, toY*100, toX*100 - currentX*100, toY*100 - currentY*100);
				repaint();
			} else {
				repaint(1000, currentX*100, currentY*100, 100, 100);
			}

			// その番号を2次元配列から探す

		} catch (Exception e) {
			System.out.println(e.getMessage() + "\r\n\r\n");
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		for (int x = 0; x < field.length; x++) {
			for (int y = 0; y < field[x].length; y++) {

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

	public Map<Direction, Integer> watchFieldReport(Point point) {
		Map<Direction, Integer> aroundMap = new HashMap<>();

		aroundMap.put(Direction.SOUTH, field[point.getX()][point.getY() - 1]);
		aroundMap.put(Direction.SOUTH, field[point.getX()][point.getY() + 1]);
		aroundMap.put(Direction.EAST, field[point.getX() + 1][point.getY()]);
		aroundMap.put(Direction.WEST, field[point.getX() - 1][point.getY()]);
		return aroundMap;
	}

}
