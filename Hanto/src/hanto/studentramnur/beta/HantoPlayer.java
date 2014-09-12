package hanto.studentramnur.beta;

import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;

import java.util.HashMap;

public class HantoPlayer {
	private HantoPlayerColor color;
	private HashMap<HantoPiece, Integer> pieces;
	private int movesMade;
	
	public HantoPlayer(HantoPlayerColor color) {
		this.color = color;
		this.pieces = new HashMap<HantoPiece, Integer>();
		this.movesMade = 0;
	}
	
	public void setPieceCount(HantoPiece piece, int count) {
		pieces.put(piece, count);
	}
	
	public void getPieceCount(HantoPiece piece) {
		pieces.get(piece);
	}
	
	public boolean decrementPieceCount(HantoPiece piece) {
		if(pieces.containsKey(piece) && pieces.get(piece) > 0) {
			pieces.put(piece, pieces.get(piece) - 1);
			return true;
		} else {
			return false;
		}
	}
	
	public void incrementMovesMade() {
		movesMade++;
	}
	
	public int getMovesMade() {
		return movesMade;
	}
	
	public HantoPlayerColor getColor() {
		return color;
	}
}
