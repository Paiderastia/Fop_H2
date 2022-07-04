package stone;

import main.TetrisGame;
import misc.Vector;
import robot.SquareColor;
import robot.SquareRobot;

public class L extends Stone {

	private static final SquareColor COLOR = SquareColor.ORANGE;

	/**
	 * Constructs a stone of type {@link L}.
	 * @param x the x coordinate of the main robot
	 * @param y the y coordinate of the main robot
	 */
	public L(TetrisGame game, int x, int y) {
		super(game);
		SquareRobot[] robots = new SquareRobot[4];
		//////////// ROTATIONS ////////////
		// . . . | . 3 . | . . 0 | 0 1 . //
		// 1 2 3 | . 2 . | 3 2 1 | . 2 . //
		// 0 . . | . 1 0 | . . . | . 3 . //
		///////////////////////////////////
		robots[0] = new SquareRobot(this, x - 1, y - 1, COLOR, Vector.of(2, 0), Vector.of(0, 2), Vector.of(-2, 0), Vector.of(0, -2));
		robots[1] = new SquareRobot(this, x - 1, y, COLOR, Vector.of(1, -1), Vector.of(1, 1), Vector.of(-1, 1), Vector.of(-1, -1));
		robots[2] = new SquareRobot(this, x, y, COLOR, Vector.of(0, 0), Vector.of(0, 0), Vector.of(0, 0), Vector.of(0, 0));
		robots[3] = new SquareRobot(this, x + 1, y, COLOR, Vector.of(-1, 1), Vector.of(-1, -1), Vector.of(1, -1), Vector.of(1, 1));
		setSquareRobots(robots);
	}

}
