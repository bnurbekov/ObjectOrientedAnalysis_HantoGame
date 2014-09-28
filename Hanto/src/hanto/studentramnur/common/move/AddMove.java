package hanto.studentramnur.common.move;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentramnur.common.HantoBoard;
import hanto.studentramnur.common.piece.HantoPieceFactory;


public class AddMove extends Move {

	public AddMove(HantoPlayerColor color, HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) {
		super(color, pieceType, from, to, MoveType.ADD);
	}

	@Override
	public boolean validate(HantoBoard board) {
		boolean isValid = true;

		if(board.isEmpty()) { // is it a new game?
			if(!board.cellIsOrigin(to)) isValid = false; // is the piece added to origin?
		}
		
		if(board.getNumPiecesOnBoard() >= 2) { //if not first two moves
			if(!(board.isAdjacentToExistingCells(to, this.getColor())
					&& !board.isAdjacentToExistingCells(to, (this.getColor() == HantoPlayerColor.BLUE) ? HantoPlayerColor.RED : HantoPlayerColor.BLUE))) {
				isValid = false;
			}
		} else {
			isValid = super.validate(board) && isValid;
		}
		
		return isValid;
	}

	@Override
	public void execute(HantoBoard board) {
		HantoPiece piece = HantoPieceFactory.getInstance().createPiece(this.getColor(), pieceType);

		board.addPiece(this.getTo(), piece);
	}
}
