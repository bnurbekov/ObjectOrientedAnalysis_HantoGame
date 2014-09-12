package hanto.studentramnur.common;

import hanto.common.HantoCoordinate;

public class HantoBoardCoordinate implements HantoCoordinate {
	
	private int x;
	private int y;
	
	public HantoBoardCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	public boolean isAdjacentTo(HantoBoardCoordinate cell) {
		int cellDifference = getCellDistance(cell);
		
		return cellDifference == 1;
	}
	
	private int getCellDistance(HantoCoordinate toCell) {
		int xDifference = this.getX() - toCell.getX();
		int yDifference = this.getY() - toCell.getY();
		int absXDifference = Math.abs(xDifference);
		int absYDifference = Math.abs(yDifference);
		
		int absSumOfDifferences = Math.abs(xDifference + yDifference);
		
		int resultingDifference = Math.max(Math.max(absXDifference, absYDifference), absSumOfDifferences);
		
		return resultingDifference;
	}
}
