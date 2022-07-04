package robot;

import fopbot.Robot;
import fopbot.World;
import main.TetrisGame;
import misc.Vector;
import stone.Stone;

public class SquareRobot extends Robot {

	///////////////
	// EXERCISES //
	///////////////
	
	// check the field
	public static int checkField(double x, double y, SquareRobot squareRobot) {
		
		// out of bounds
		if(x<0 || x>= World.getWidth() || y<0 || y>=World.getHeight())
			return -1;
		//check the robots in his own stones, which are the possible movements
		for(SquareRobot friends : squareRobot.getRelatedStone().getSquareRobots()) {
			if(friends.getX()==x && friends.getY()==y)
					return 2;
		}
		//check all square robots on the board
		TetrisGame game = squareRobot.getRelatedStone().getRelatedGame();
		for(Stone stones : game.getStoneArray()) {
			if(stones == null)
				continue;
			for(SquareRobot sq: stones.getSquareRobots()) {
				if(sq.getX() == x && sq.getY()== y && sq.isTurnedOn()) 
					return 1;
			}
		}
		//field is free
		return 0;
	}
	
	/**
	 * Returns an array containing all possible movements of this square robot.
	 * @return an array containing all possible movements of this square robot
	 */
	public Vector[] getPossibleMovements() {
		// TODO Exercise H1.1
		int possibleMovementCount = 0;
		int allMovementsCount = 0;
		
		
		//find out all movements which exist
		Vector[] allMovements = new Vector[210];
		for(int i=-7; i<8; i++) {
			for(int j=2; j>-12; j--) {
				allMovements[allMovementsCount] = new Vector(i,j);
				allMovementsCount++;
			}
		}
		
		// use checkField to find out the number of possible movements
		for(int i=0; i<allMovements.length; i++) {
			
			double checkX = this.getX() + allMovements[i].x;
			double checkY = this.getY() + allMovements[i].y;
			if(SquareRobot.checkField(checkX, checkY, this) ==0 || SquareRobot.checkField(checkX, checkY, this) ==2)
				possibleMovementCount++;
		}
		// if there are no possible movements
		if(possibleMovementCount == 0)
			return null;
		
		Vector[] possibleMovements = new Vector[possibleMovementCount];
		
		//counter for the array
		int index = 0;
		
		// compute the coordinate of the possible vectors
		for(int i=0; i<allMovements.length; i++) {
			
			double checkX = this.getX() + allMovements[i].x;
			double checkY = this.getY() + allMovements[i].y;
			if(SquareRobot.checkField(checkX, checkY, this) ==0 || SquareRobot.checkField(checkX, checkY, this) ==2){
				possibleMovements[index] = allMovements[i];
				index++;
			}
			
		}
		return possibleMovements;
	}

	/**
	 * Returns if this square robot can be moved by the given vector.
	 * @param vector the vector
	 * @return if this square robot can be move by the given vector
	 */
	public boolean canMove(Vector vector) {
		// TODO Exercise H1.2
		Vector[] possibleMovements = getPossibleMovements();
		for(int i=0; i<getPossibleMovements().length; i++) {
			if(possibleMovements[i] == null)
				return false;
			if(vector.x == possibleMovements[i].x && vector.y == possibleMovements[i].y) 
				return true;
			
		}
		return false;
	}

	/**
	 * Rotates this square robot to the left.
	 */
	//for H2.1
	public int numbersOfLeftTurns;
	
	public void rotateLeft() {
		// TODO Exercise H2.1
		
		// Die Anfangszustaende von square robots in a stone is certain.(picture 1 in I,J,L…）
		Vector[] instructions = this.getRotationVectors(); 
		
		if(numbersOfLeftTurns % 4==0) {
			this.move(instructions[0]);
		}
		if(numbersOfLeftTurns % 4==1) {
			this.move(instructions[1]);
		}
		if(numbersOfLeftTurns % 4==2) {
			this.move(instructions[2]);
		}
		if(numbersOfLeftTurns % 4==3) {
			this.move(instructions[3]);
		}
		
		numbersOfLeftTurns++;
	}
	
	// ---------------------------------------------------------------------------------------------------- //
	
	private int turnOffIteration = -1;
	private final Stone relatedStone;
	private final Vector[] rotationVectors;

	/**
	 * Constructs a {@link SquareRobot}.
	 * @param stone           the related stone
	 * @param x               the x coordinate
	 * @param y               the y coordinate
	 * @param color           the color
	 * @param rotationVectors the rotation vectors
	 */
	public SquareRobot(Stone stone, int x, int y, SquareColor color, Vector... rotationVectors) {
		super(x, y);
		this.relatedStone = stone;
		this.rotationVectors = rotationVectors;
		setImageId(color.toString());
	}

	/**
	 * Returns the related stone of this square robot.
	 * @return the related stone of this square robot
	 */
	public Stone getRelatedStone() {
		return relatedStone;
	}

	/**
	 * Returns the rotation vector array of this square robot.
	 * @return the rotation vector array of this square robot.
	 */
	public Vector[] getRotationVectors() {
		return rotationVectors;
	}

	/**
	 * Returns the iteration in which this robot was turned off.<br>
	 * If the robot was not turned off, {@code -1} will be returned.
	 * @return the iteration in which this robot was turned off or {@code -1} if this robot was not turned off
	 */
	public int getTurnOffIteration() {
		return turnOffIteration;
	}

	/**
	 * Moves this robot by the given vector.
	 * @param vector the vector
	 */
	public void move(Vector vector) {
		setX(getX() + vector.x);
		setY(getY() + vector.y);
	}

	/**
	 * Moves this robot by the vector (0,-1).
	 */
	public void moveDown() {
		move(Vector.of(0, -1));
	}

	/**
	 * Moves this robot by the vector (-1,0).
	 */
	public void moveLeft() {
		move(Vector.of(-1, 0));
	}

	/**
	 * Moves this robot by the vector (1, 0).
	 */
	public void moveRight() {
		move(Vector.of(1, 0));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void turnOff() {
		if (isTurnedOff())
			return;
		super.turnOff();
		turnOffIteration = getRelatedStone().getRelatedGame().getIteration();
	}

}
