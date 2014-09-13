package hanto.studentramnur.beta;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

import java.util.HashMap;

public class HantoPlayer {
	private HantoPlayerColor color;
	private HashMap<HantoPieceType, Integer> pieces;
	private int movesMade;
	
	public HantoPlayer(HantoPlayerColor color) {
		this.color = color;
		this.pieces = new HashMap<HantoPieceType, Integer>();
		this.movesMade = 0;
	}
	
	public void setPieceCount(HantoPieceType piece, int count) {
		pieces.put(piece, count);
	}
	
	public void getPieceCount(HantoPieceType piece) {
		pieces.get(piece);
	}
	
	public boolean decrementPieceCount(HantoPieceType piece) {
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

	public boolean hasPlacedButterfly() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasPieces() {
		// TODO Auto-generated method stub
		return false;
	}
}
