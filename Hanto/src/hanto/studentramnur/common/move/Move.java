package hanto.studentramnur.common.move;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentramnur.common.HantoBoard;

public abstract class Move {
	public HantoPlayerColor color;
	public HantoPieceType pieceType;
	public HantoCoordinate from;
	public HantoCoordinate to;
	public MoveResult result;
	protected MoveType moveType;
	
	public Move(HantoPlayerColor color, HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to, MoveType moveType) {
		this.color = color;
		this.pieceType = pieceType;
		this.from = from;
		this.to = to;
		this.result = MoveResult.OK;
		this.moveType = moveType;
	}

	public HantoPlayerColor getColor() {
		return color;
	}

	public HantoPieceType getPieceType() {
		return pieceType;
	}

	public HantoCoordinate getFrom() {
		return from;
	}

	public HantoCoordinate getTo() {
		return to;
	}
	
	public MoveResult getResult() {
		return result;
	}

	public boolean validate(HantoBoard board) {
		boolean isValid = true;
		
		if(this.isCellOccupied(board)) {
			isValid = false;
		}
		
		if(!board.isAdjacentToExistingCells(to, null)) isValid = false;
		
		return isValid;
	}
	
	public abstract void execute(HantoBoard board);

	protected boolean isCellOccupied(HantoBoard board) {		
		return !board.isCellEmpty(this.getTo()); 
	}

	public MoveType getMoveType() {
		return moveType;
	}
}
