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

}
