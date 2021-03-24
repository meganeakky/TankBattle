package control;

import java.util.Random;

public enum Direction {

	NORTH,
	SOUTH,
	EAST,
	WEST;

	public static Direction getRandom() {
		int rand = new Random().nextInt(Direction.values().length);
		return Direction.values()[rand];
	}
}
