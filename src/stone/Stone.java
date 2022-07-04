package stone;

import main.TetrisGame;
import misc.Vector;
import robot.SquareRobot;

public abstract class Stone {

	///////////////
	// EXERCISES //
	///////////////

	/**
	 * Tries to rotate all square robots of this stone to the left.
	 * @return if the rotation was successful
	 */
	public boolean rotateAllLeft() {
		// TODO Exercise H2.2
		SquareRobot[] members = this.getSquareRobots();
		for(int i=0; i<4; i++) {
			if(members[0].canMove(rotationLeft(0,i)) && members[1].canMove(rotationLeft(1,i))&& members[2].canMove(rotationLeft(2,i))&& members[3].canMove(rotationLeft(3,i))) {
				members[2].rotateLeft();
				members[3].rotateLeft();
				members[0].rotateLeft();
				members[1].rotateLeft();
				return true;
			}
		}
		return false;
	}
	
	// find the rotation vector of each robot in every turn
	public Vector rotationLeft(int numberOfRobot, int numberOfVector) {
		SquareRobot[] members = this.getSquareRobots();
		Vector rotation = members[numberOfRobot].getRotationVectors()[numberOfVector];
		
		return rotation;
	}

	/**
	 * Handles the given key input.
	 * @param key the key input
	 */
	public void handleKeyInput(byte key) {
		// TODO Exercise H3
		if(key==0)
			rotateAllLeft();
		if(key==1)
			moveAllLeft();
		if(key==2)
			moveAllDown();
		if(key==3)
			moveAllRight();
		if(key==4) {
			SquareRobot[] robots = this.getSquareRobots();
			Vector down = new Vector(0,-1);
			while(robots[0].canMove(down) && robots[1].canMove(down) && robots[2].canMove(down) && robots[3].canMove(down)) {
				moveAllDown();
			}
			
		}
	}

	// ---------------------------------------------------------------------------------------------------- //

	private final TetrisGame game;

	private SquareRobot[] robots;

	/**
	 * Constructs a {@link Stone}.
	 * @param game the game using this stone
	 */
	public Stone(TetrisGame game) {
		this.game = game;
	}

	/**
	 * Returns the game using this stone.
	 * @return the game using this stone
	 */
	public TetrisGame getRelatedGame() {
		return game;
	}

	/**
	 * Returns the square robot array of this stone.
	 * @return the square robot array of this stone
	 */
	public SquareRobot[] getSquareRobots() {
		return robots;
	}

	/**
	 * Tries to move all square robots of this stone by the given vector.
	 * @param vector the vector
	 * @return if the movement was successful
	 */
	public boolean moveAll(Vector vector) {
		for (SquareRobot square : robots)
			if (!square.canMove(vector))
				return false;
		for (SquareRobot square : robots)
			square.move(vector);
		return true;
	}

	/**
	 * Tries to move down all square robots of this stone.
	 * @return if the movement was successful
	 */
	public boolean moveAllDown() {
		return moveAll(Vector.of(0, -1));
	}

	/**
	 * Tries to move left all square robots of this stone.
	 * @return if the movement was successful
	 */
	public boolean moveAllLeft() {
		return moveAll(Vector.of(-1, 0));
	}

	/**
	 * Tries to move right all square robots of this stone.
	 * @return if the movement was successful
	 */
	public boolean moveAllRight() {
		return moveAll(Vector.of(1, 0));
	}

	/**
	 * Sets the array of square robots for this stone.
	 * @param if the array of square robots
	 */
	public void setSquareRobots(SquareRobot... robots) {
		this.robots = robots;
	}

}
